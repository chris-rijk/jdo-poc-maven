package jdotest.model.database;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanyMapBase;
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
}
