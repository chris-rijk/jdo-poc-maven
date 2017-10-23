package jdotest.model.database;

import javax.jdo.PersistenceManager;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditServiceInstance;

public class AuditInstancesService extends AuditHandlerCommon implements IAuditInstancesService {

    public AuditInstancesService(Audit audit) {
        super(audit);
    }

    @Override
    public AuditServiceInstancesMap StartInstancesAudit(AuditServiceInstancesMapBase serviceInstance) {
        AuditServiceInstancesMap ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditServiceInstance asi = new AuditServiceInstance(auditId, serviceInstance);
            pm.makePersistent(asi);
            ret = asi.toAuditServiceInstancesMap(audit);
        }

        return ret;
    }
}
