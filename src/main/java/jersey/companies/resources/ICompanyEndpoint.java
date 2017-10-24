package jersey.companies.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import jersey.companies.Company;
import jersey.companies.CompanyBase;
import jersey.companies.PagedCompanies;

/**
 *
 * @author crijk
 */
@Api(value = "Service for Companies and Clients")
@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public interface ICompanyEndpoint {

    @GET
    @Path("{companyId}")
    @ApiOperation(value = "Returns company details", notes = "Returns the specified company, if it exists")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful retrieval of company detail", response = Company.class),
        @ApiResponse(code = 401, message = "Invalid authentication"),
        @ApiResponse(code = 404, message = "Company does not exist"),
        @ApiResponse(code = 405, message = "Authenticated user not allowed to perform this operation"),
        @ApiResponse(code = 500, message = "Internal server error")}
    )
    Company get(
            @ApiParam(name = "companyId", value = "Numeric ID of the company", required = true)
            @PathParam("companyId") long companyId);

    @POST
    @Path("")
    @ApiOperation(value = "Creates a new company", notes = "Creates the specified company and returns the new instance")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully created company", response = Company.class),
        @ApiResponse(code = 401, message = "Not authorised to use this service"),
        @ApiResponse(code = 405, message = "Authenticated user not allowed to perform this operation"),
        @ApiResponse(code = 422, message = "Provided company details are invalid"),
        @ApiResponse(code = 500, message = "Internal server error")}
    )
    Company create(
            @ApiParam(name = "company", value = "The details of the company to add", required = true) CompanyBase company);

    @PUT
    @Path("{companyId}")
    @ApiOperation(value = "Updates an existing company", notes = "Updates the specified company and returns the new values")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Successfully updated company"),
        @ApiResponse(code = 401, message = "Not authorised to use this service"),
        @ApiResponse(code = 404, message = "Company does not exist"),
        @ApiResponse(code = 405, message = "Authenticated user not allowed to perform this operation"),
        @ApiResponse(code = 422, message = "Provided company details are invalid"),
        @ApiResponse(code = 500, message = "Internal server error")}
    )
    void update(
            @ApiParam(name = "companyId", value = "Numeric ID of the company", required = true) @PathParam("companyId") long companyId,
            @ApiParam(name = "company", value = "The updated values for the company", required = true) CompanyBase company);

    @GET
    @Path("")
    @ApiOperation(value = "Returns a list of company details", notes = "Returns 0 or more companies, with pagination")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "0 or more companies returned", response = PagedCompanies.class),
        @ApiResponse(code = 401, message = "Invalid authentication"),
        @ApiResponse(code = 405, message = "Authenticated user not allowed to perform this operation"),
        @ApiResponse(code = 500, message = "Internal server error")}
    )
    PagedCompanies search(
            @ApiParam(name = "isEnabled", value = "If true then only return companies that are enabled", required = false)
            @QueryParam("isEnabled") Integer isEnabled,
            @ApiParam(name = "subscriptionId", value = "If set then only return companies that match this subscription ID", required = false)
            @QueryParam("subscriptionId") String subscriptionId,
            @ApiParam(name = "skip", value = "If set then ignore the first [skip] number of results", required = false)
            @QueryParam("skip") Integer skip,
            @ApiParam(name = "take", value = "If set then only return [take] number of results", required = false)
            @QueryParam("take") Integer take
    );
}
