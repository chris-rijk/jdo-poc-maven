package jdotest.model.database;

import javax.jdo.JDOObjectNotFoundException;
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
            pm.makePersistent(a);
            AuditServiceInstance asi = a.CreateAuditServiceInstance(serviceInstance);
            pm.makePersistent(asi);
            ret = asi.toAuditServiceInstancesMap();
        }

        return ret;
    }

    @Override
    public AuditServiceInstancesMap GetInstancesAudit(long id) throws JDOObjectNotFoundException {
        AuditServiceInstancesMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditServiceInstance audit = pm.getObjectById(AuditServiceInstance.class, id);
            if (audit != null) {
                ret = audit.toAuditServiceInstancesMap();
            }
        }

        return ret;
    }
}
