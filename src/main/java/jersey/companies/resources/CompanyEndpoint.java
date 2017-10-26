package jersey.companies.resources;

import javax.jdo.JDOObjectNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import jdotest.dto.CompanyMap;
import jdotest.dto.CompanySearchMap;
import jdotest.dto.PagedListMap;
import jdotest.dto.enums.HttpRequestType;
import jdotest.dto.enums.HttpResponseType;
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
    ContainerRequestContext requestCtx;
    @Context
    private ICompanyService companyService;

    @Override
    public Company get(long companyId) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanyGet);
       
        CompanyMap c = null;
        try {
            c = companyService.GetCompany(companyId);
        } catch (JDOObjectNotFoundException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Company response = new Company(c);
        ra.markResponseWithJson(Status.OK, HttpResponseType.Success, response);
        return response;
    }

    @Override
    public Company create(CompanyBase company) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanyCreate);
        CompanyMap c = companyService.CreateCompany(company.toMap());
        Company response = new Company(c);
        ra.markResponseWithJson(Status.CREATED, HttpResponseType.Success, response);
        return response;
    }

    @Override
    public void update(long companyId, CompanyBase company) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanyCreate);
        companyService.UpdateCompany(companyId, company.toMap());
        ra.markResponse(Status.NO_CONTENT, HttpResponseType.Success);
    }

    @Override
    public PagedCompanies search(String name, Boolean isEnabled, String subscriptionId, Integer skip, Integer take) {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestCtx);
        ra.StartHttpRequest(HttpRequestType.CompanySearch);
        PagedListMap<CompanyMap> list = companyService.SearchCompanies(new CompanySearchMap(name, isEnabled, subscriptionId, skip, take));

        PagedCompanies response = new PagedCompanies(list);
        ra.markResponseWithJson(Status.OK, HttpResponseType.Success, response);
        return response;
    }
}
