package ta.microservices.common.service.lifecycle;

import javax.ws.rs.container.ContainerRequestContext;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.DiagnosticType;
import jdotest.model.interfaces.IAuditHttpRequestsService;

public class RequestAuditing {

    private static final String CONTEXT_KEY = "ta.microservices.common.service.lifecycle.RequestAuditing";
    private final IAuditHttpRequestsService requestService;

    public RequestAuditing(IAuditHttpRequestsService requestService) {
        this.requestService = requestService;
    }
    
    public static RequestAuditing GetFromContext(ContainerRequestContext requestContext) {
        Object o = requestContext.getProperty(CONTEXT_KEY);
        if (o != null && o instanceof RequestAuditing) {
            return (RequestAuditing)o;
        }
        return null;
    }

    public static void SetFromContext(ContainerRequestContext requestContext, RequestAuditing ra) {
        requestContext.setProperty(CONTEXT_KEY, ra);
    }
    

    public void AuditDiagnostics(DiagnosticType type, String message) {
        requestService.AuditDiagnostics(new DiagnosticAuditMapBase(type, message));
    }
}
