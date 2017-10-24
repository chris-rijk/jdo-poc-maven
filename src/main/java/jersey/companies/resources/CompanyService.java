package jersey.companies.resources;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import jdotest.model.interfaces.IAuditInstancesService;
import jersey.companies.Company;
import jersey.companies.CompanyBase;
import jersey.companies.PagedCompanies;

/**
 *
 * @author crijk
 */
public class CompanyService implements ICompanyService {

    @Context
    private HttpServletRequest req;
    @Context
    private ServletContext ctx;
    @Context
    private HttpServletResponse response;
    @Context
    private IAuditInstancesService instancesService;

    @Override
    public Company get(long companyId) {
        addMetadataHeaders();
        return new Company(instancesService.GetAuditId(), "username", "platform");
    }

    @Override
    public Company create(CompanyBase company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(long companyId, CompanyBase company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addMetadataHeaders() {
        // Todo: turn into response filter
        if (response != null) {
            response.addDateHeader("ServiceResponseTime", System.currentTimeMillis());
            response.addHeader("ServiceInstanceId", Long.toString(instancesService.GetAuditId()));
        }
    }

    @Override
    public PagedCompanies search(Integer isEnabled, String subscriptionId, Integer skip, Integer take) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
