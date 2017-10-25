package jersey.companies.resources;

import javax.jdo.JDOObjectNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanySearchMap;
import jdotest.dto.PagedListMap;
import jdotest.dto.enums.HttpRequestType;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.interfaces.ICompanyService;
import jersey.companies.Company;
import jersey.companies.CompanyBase;
import jersey.companies.PagedCompanies;
import ta.microservices.common.service.lifecycle.RequestAuditing;

/**
 *
 * @author crijk
 */
public class CompanyEndpoint implements ICompanyEndpoint {

    @Context
    Request request;
    @Context
    Response response;
    @Context
    ContainerRequestContext requestCtx;

    @Context
    private IAuditInstancesService instancesService;
    @Context
    private ICompanyService companyService;

    @Override
    public Company get(long companyId) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanyGet);
        addMetadataHeaders();

        CompanyMap c = null;
        try {
            c = companyService.GetCompany(companyId);
        } catch (JDOObjectNotFoundException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new Company(c);
    }

    @Override
    public Company create(CompanyBase company) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanyCreate);
        CompanyMap c = companyService.CreateCompany(company.toMap());
        return new Company(c);
    }

    @Override
    public void update(long companyId, CompanyBase company) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanyCreate);
        companyService.UpdateCompany(companyId, company.toMap());
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
    public PagedCompanies search(String name, Boolean isEnabled, String subscriptionId, Integer skip, Integer take) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanySearch);
        PagedListMap<CompanyMap> list = companyService.SearchCompanies(new CompanySearchMap(name, isEnabled, subscriptionId, skip, take));
        return new PagedCompanies(list);
    }
}
