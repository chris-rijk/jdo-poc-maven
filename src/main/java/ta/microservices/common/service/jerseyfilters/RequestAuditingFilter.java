package ta.microservices.common.service.jerseyfilters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditHttpRequestsService;
import jdotest.model.interfaces.IAuditInstancesService;
import org.glassfish.jersey.message.internal.ReaderWriter;
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

        String body = null;
        if (requestContext.hasEntity()) {
            body = readEntityStream(requestContext);
        }

        RequestAuditing ra = new RequestAuditing(requestContext, requestService, body, instancesService.GetAuditId());
        ra.SetFromContext(requestContext);
        requestService.SetAuditNameValuePairs(NameValuePairType.HttpRequestHeaders, MultimapToMap.ToMap(requestContext.getHeaders()));
    }

    private String readEntityStream(ContainerRequestContext requestContext) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        final InputStream inputStream = requestContext.getEntityStream();
        final StringBuilder builder = new StringBuilder();
        try {
            ReaderWriter.writeTo(inputStream, outStream);
            byte[] requestEntity = outStream.toByteArray();
            if (requestEntity.length == 0) {
                builder.append("");
            } else {
                builder.append(new String(requestEntity));
            }
            requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
        } catch (IOException ex) {
            return null;
        }
        return builder.toString();
    }

}
