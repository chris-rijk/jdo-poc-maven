package ta.microservices.common.service.lifecycle;

import java.net.URI;
import java.util.Map;
import javax.ws.rs.container.ContainerRequestContext;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMapBase;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.DiagnosticType;
import jdotest.dto.enums.HttpRequestSourceType;
import jdotest.dto.enums.HttpRequestType;
import jdotest.dto.enums.HttpResponseType;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditHttpRequestsService;

public class RequestAuditing {

    private static final String CONTEXT_KEY = "ta.microservices.common.service.lifecycle.RequestAuditing";
    private final IAuditHttpRequestsService requestService;
    private final URI path;
    private String body;
    private HttpRequestSourceType source;

    public RequestAuditing(ContainerRequestContext requestContext, IAuditHttpRequestsService requestService, String body) {
        this.requestService = requestService;
        this.path = requestContext.getUriInfo().getAbsolutePath();
        this.body = body;
        this.source = HttpRequestSourceType.Live; // TODO:
    }
    
    public static RequestAuditing GetFromContext(ContainerRequestContext requestContext) {
        Object o = requestContext.getProperty(CONTEXT_KEY);
        if (o != null && o instanceof RequestAuditing) {
            return (RequestAuditing)o;
        }
        return null;
    }

    public void SetFromContext(ContainerRequestContext requestContext) {
        requestContext.setProperty(CONTEXT_KEY, this);
    }
    
    public void StartHttpRequest(HttpRequestType requestType) {
        requestService.StartHttpRequest(new AuditHttpRequestsMapBase(path, body, requestType, source));
    }
    
    public void SetHttpResponse(HttpResponseType response, int statusCode, String body, Map<String,String> headers) {
        requestService.SetHttpResponse(new AuditHttpResponseMapBase(response, statusCode, body));
        requestService.SetAuditNameValuePairs(NameValuePairType.HttpResponseHeaders, headers);
    }

    public void AuditDiagnostics(DiagnosticType type, String message) {
        requestService.AuditDiagnostics(new DiagnosticAuditMapBase(type, message));
    }
}
