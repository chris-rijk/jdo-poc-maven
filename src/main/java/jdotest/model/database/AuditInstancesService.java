package jdotest.model.database;

import javax.jdo.PersistenceManager;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.AuditType;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditServiceInstance;

public class AuditInstancesService implements IAuditInstancesService {
    @Override
    public AuditServiceInstancesMap CreateInstancesAudit(AuditServiceInstancesMapBase serviceInstance) {
        AuditServiceInstancesMap ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Audit a = new Audit(AuditType.ServiceInstance);
            AuditServiceInstance asi = a.CreateAuditServiceInstance(serviceInstance);
            pm.makePersistent(a);
            ret = asi.toAuditServiceInstancesMap();
        }

        return ret;
    }
}
