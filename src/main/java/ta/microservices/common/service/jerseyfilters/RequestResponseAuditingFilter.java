package ta.microservices.common.service.jerseyfilters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import jdotest.dto.enums.HttpRequestType;
import ta.microservices.common.service.lifecycle.RequestAuditing;
import ta.microservices.common.utils.MultimapToMap;

@Provider
public class RequestResponseAuditingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        RequestAuditing ra = RequestAuditing.GetFromContext(requestContext);
        if (ra == null) {
            return;
        }

        if (!ra.getHasStartedRequest()) {
            ra.StartHttpRequest(HttpRequestType.Unknown);
        }

        if (ra.getHasSetResponse()) {
            responseContext.setStatusInfo(ra.getResponseStatus());
        }

        MultivaluedMap<String, Object> rawHeaders = responseContext.getHeaders();
        rawHeaders.add("ServiceResponseTime", System.currentTimeMillis());
        rawHeaders.add("ServiceInstanceId", ra.getServiceAuditId());
        rawHeaders.add("HttpRequestAuditId", ra.getAuditId());
        
        ra.SetHttpResponse(responseContext.getStatus(), MultimapToMap.ToMap(responseContext.getStringHeaders()));
    }
}
