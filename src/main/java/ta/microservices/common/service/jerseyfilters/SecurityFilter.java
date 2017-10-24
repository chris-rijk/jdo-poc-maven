package ta.microservices.common.service.jerseyfilters;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import jdotest.dto.enums.DiagnosticType;
import ta.microservices.common.service.lifecycle.RequestAuditing;
import ta.microservices.common.service.security.Authorizer;
import ta.microservices.common.service.security.TokenUtil;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        RequestAuditing ra = RequestAuditing.GetFromContext(requestContext);
        ra.AuditDiagnostics(DiagnosticType.Authorisation, "Starting auth header processing on '" + authHeader + "'");

        Authorizer authorizer = getAuthorisation(authHeader);
        if (authorizer == null) {
            ra.AuditDiagnostics(DiagnosticType.Authorisation, "Using any authorizer");
            authorizer = Authorizer.AnyAuthorizer;
        }
        requestContext.setSecurityContext(authorizer);
    }

    private Authorizer getAuthorisation(String authorizationHeader) {
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String strToken = authorizationHeader.substring("Bearer".length()).trim();

                TokenUtil tu = new TokenUtil(strToken);
                if (tu.isValid()) {
                    String name = tu.getName();
                    String[] roles = tu.getRoles();
                    int version = tu.getVersion();
                    if (name != null && roles.length != 0 && version != -1) {
                        return new Authorizer(roles, name, tu.getExpiration(), true);
                    }
                }
            }
        } catch (Exception e) {
            // todo:
        }

        return null;
    }
}
