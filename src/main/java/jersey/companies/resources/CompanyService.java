package jersey.companies.resources;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
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
    Request request;
    @Context
    Response response;
    @Context
    ContainerRequestContext requestCtx;
    
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
            MultivaluedMap<String, Object> headers = response.getHeaders();
            headers.add("ServiceResponseTime", System.currentTimeMillis());
            headers.add("ServiceInstanceId", instancesService.GetAuditId());
        }
    }

    @Override
    public PagedCompanies search(Integer isEnabled, String subscriptionId, Integer skip, Integer take) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
