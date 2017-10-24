package ta.microservices.common.service.jerseyfilters;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditHttpRequestsService;
import jdotest.model.interfaces.IAuditInstancesService;
import ta.microservices.common.service.lifecycle.RequestAuditing;
import ta.microservices.common.utils.MultimapToMap;

@Provider
@Priority(0)
public class RequestAuditingFilter implements ContainerRequestFilter {
    @Context
    private IAuditInstancesService instancesService;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        IAuditHttpRequestsService requestService = instancesService.CreateHttpRequest();
        RequestAuditing ra = new RequestAuditing(requestService);
        RequestAuditing.SetFromContext(requestContext, ra);
        requestService.SetAuditNameValuePairs(NameValuePairType.HttpRequestHeaders, MultimapToMap.ToMap(requestContext.getHeaders()));
    }
}
