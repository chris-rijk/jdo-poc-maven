package jdotest.model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanyMapBase;
import jdotest.dto.CompanySearchMap;
import jdotest.dto.PagedListMap;
import jdotest.dto.enums.CompanyStatusType;
import jdotest.model.enums.CompaniesOptionalDataType;
import jdotest.model.interfaces.ICompanyService;
import jdotest.model.modelClasses.Company;

public class CompanyService implements ICompanyService {

    @Override
    public CompanyMap CreateCompany(CompanyMapBase company) {
        CompanyMap ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Company c = new Company(company);
            pm.makePersistent(c);
            ret = c.toCompanyMap();
        }

        return ret;
    }

    @Override
    public boolean UpdateCompany(long id, CompanyMapBase company) throws JDOObjectNotFoundException {
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Company c = pm.getObjectById(Company.class, id);
            if (c == null) {
                return false;
            }
            c.updateFrom(company);
            pm.makePersistent(c);
        }
        return true;
    }

    @Override
    public CompanyMap GetCompany(long id) throws JDOObjectNotFoundException {
        CompanyMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Company c = pm.getObjectById(Company.class, id);
            if (c != null) {
                ret = c.toCompanyMap();
            }
        }

        return ret;
    }

    @Override
    public PagedListMap<CompanyMap> SearchCompanies(CompanySearchMap search) {
        PagedListMap<CompanyMap> ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {

            Query<Company> query = CreateQuery(pm, search);
            List<CompanyMap> list = new ArrayList<>();
            for (Company entry : query.executeList()) {
                list.add(entry.toCompanyMap());
            }

            ret = new PagedListMap<>(list, list.size(), 0, 0);
        }
        return ret;
    }

    private Query<Company> CreateQuery(PersistenceManager pm, CompanySearchMap search) {
        Map<String, Object> parameters = new HashMap<>();
        Query<Company> query = pm.newQuery(Company.class);

        StringBuilder filter = new StringBuilder();
        if (search.getName() != null) {
            filter.append("this.Name == :name");
            parameters.put("name", search.getName());
        }

        if (search.getIsEnabled() != null) {
            if (filter.length() > 0) filter.append(" && ");
            filter.append("this.StatusType == :statusType");
            parameters.put("statusType", search.getIsEnabled()? CompanyStatusType.Enabled : CompanyStatusType.Disabled);
        }

        if (search.getPlatform()!= null) {
            if (filter.length() > 0) filter.append(" && ");
            filter.append("this.optionalData.get(:platformKey).Value == :platformValue");
            parameters.put("platformKey", CompaniesOptionalDataType.Platform.getValue());
            parameters.put("platformValue", search.getPlatform());
        }
        
        query.setNamedParameters(parameters);
        query.filter(filter.toString());
        return query;
    }
}
