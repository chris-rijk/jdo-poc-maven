package jdotest.model.database;

import javax.jdo.PersistenceManager;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditServiceInstance;

public class AuditInstancesService implements IAuditInstancesService {

    private final Audit audit;
    private final long auditId;

    protected AuditInstancesService(Audit audit) {
        this.audit = audit;
        this.auditId = audit.getId();
    }

    @Override
    public long GetAuditId() {
        return auditId;
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
