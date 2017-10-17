package jdotest.model.interfaces;

import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanyMapBase;

public interface ICompanyService {
    CompanyMap CreateCompany(CompanyMapBase company);
    boolean UpdateCompany(long id, CompanyMapBase company);
    CompanyMap GetCompany(long id) throws JDOObjectNotFoundException;
}
