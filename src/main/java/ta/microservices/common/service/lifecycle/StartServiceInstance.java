package ta.microservices.common.service.lifecycle;

import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.DiagnosticType;
import jdotest.model.database.AuditService;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.interfaces.IAuditService;

public class StartServiceInstance {

    public static void Start(JerseyConfig jerseyConfig, SystemConfiguration system) {
        IAuditService auditService = new AuditService();
        IAuditInstancesService instancesService = auditService.CreateInstancesAudit();
        String url = system.getBindURI().toString();
        AuditServiceInstancesMap serviceInstance = instancesService.StartInstancesAudit(new AuditServiceInstancesMapBase(url, "docker"));

        jerseyConfig.setServiceInstance(instancesService);
        jerseyConfig.AuditDiagnostics(DiagnosticType.Startup, "Starting instance " + serviceInstance.getAuditId() + " on " + url);
    }
}
