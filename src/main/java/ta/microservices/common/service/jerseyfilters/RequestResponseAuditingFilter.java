package ta.microservices.common.service.jerseyfilters;

import java.io.IOException;
import java.util.Map;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import jdotest.dto.enums.HttpRequestType;
import jdotest.dto.enums.HttpResponseType;
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
        
        Map<String,String> headers = MultimapToMap.ToMap(responseContext.getStringHeaders());
        String body = null; // TODO:
        ra.SetHttpResponse(HttpResponseType.Success, responseContext.getStatus(), body, headers);
    }
}
