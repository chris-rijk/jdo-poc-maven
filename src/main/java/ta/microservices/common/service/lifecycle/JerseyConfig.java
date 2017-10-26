package ta.microservices.common.service.lifecycle;

import java.time.Instant;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.DiagnosticType;
import jdotest.model.interfaces.IAuditInstancesService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import ta.microservices.common.service.jerseyfilters.CustomMessageBodyWriter;
import ta.microservices.common.service.jerseyfilters.ExceptionMappingThrowable;
import ta.microservices.common.service.jerseyfilters.RequestAuditingFilter;
import ta.microservices.common.service.jerseyfilters.RequestResponseAuditingFilter;
import ta.microservices.common.service.jerseyfilters.SecurityFilter;

public class JerseyConfig extends ResourceConfig {

    private IAuditInstancesService instancesService;

    final protected void RegisterDefault() {
        RegisterSerializer();
        RegisterSecurityRoleNames();
        RegisterRequestAuditingFilter();
        RegisterExceptionMappers();
        RegisterJWTSecurityFilter();
        RegisterSwagger();
        RegisterInstancesService();
    }

    protected void RegisterRequestAuditingFilter() {
        register(RequestAuditingFilter.class);
        register(RequestResponseAuditingFilter.class);
        register(CustomMessageBodyWriter.class);
    }

    protected void RegisterExceptionMappers() {
        register(ExceptionMappingThrowable.class);
    }
    
    protected void RegisterJWTSecurityFilter() {
        register(SecurityFilter.class);
    }

    protected void RegisterSecurityRoleNames() {
        register(RolesAllowedDynamicFeature.class);
    }

    protected void RegisterSerializer() {
        register(JsonSerialisation.getProvider());
    }

    protected void RegisterSwagger() {
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    }

    void setServiceInstance(IAuditInstancesService instancesService) {
        this.instancesService = instancesService;
    }

    private void RegisterInstancesService() {
        if (instancesService != null) {
            register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(instancesService).to(IAuditInstancesService.class);
                }
            });
        }
    }

    public void AuditDiagnostics(DiagnosticType type, String message) {
        instancesService.AuditDiagnostics(new DiagnosticAuditMapBase(type, message));
        System.out.printf("[%s] [%s] %s%n", Instant.now().toString(), type.name(), message);
    }
}
