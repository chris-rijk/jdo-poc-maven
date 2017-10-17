package jdotest;

import jdotest.dto.CompanyMap;
import jdotest.dto.CompanyMapBase;
import jdotest.dto.enums.CompanyStatusType;
import jdotest.model.database.CompanyService;

public class CompaniesServiceMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CompanyMapBase c = new CompanyMapBase("foobar", CompanyStatusType.Enabled, "the plat");
        CompanyService s = new CompanyService();
        CompanyMap cm = s.CreateCompany(c);
    }
}
