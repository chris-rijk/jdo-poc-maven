package ta.microservices.common.service.lifecycle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import java.time.Instant;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.DiagnosticType;
import jdotest.model.interfaces.IAuditInstancesService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import ta.microservices.common.service.jerseyfilters.SecurityFilter;

public class JerseyConfig extends ResourceConfig {

    private IAuditInstancesService instancesService;

    final protected void RegisterDefault() {
        RegisterSerializer();
        RegisterSecurityRoleNames();
        RegisterJWTSecurityFilter();
        RegisterSwagger();
        RegisterInstancesService();
    }

    protected void RegisterJWTSecurityFilter() {
        register(SecurityFilter.class);
    }

    protected void RegisterSecurityRoleNames() {
        register(RolesAllowedDynamicFeature.class);
    }

    protected void RegisterSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);
        register(provider);
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
