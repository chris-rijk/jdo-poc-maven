package jdotest.model.interfaces;

import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanyMapBase;
import jdotest.dto.CompanySearchMap;
import jdotest.dto.PagedListMap;

public interface ICompanyService {
    CompanyMap CreateCompany(CompanyMapBase company);
    boolean UpdateCompany(long id, CompanyMapBase company);
    CompanyMap GetCompany(long id) throws JDOObjectNotFoundException;
    PagedListMap<CompanyMap> SearchCompanies(CompanySearchMap search);
}
