package jdotest.model.database;

import javax.jdo.PersistenceManager;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;
import jdotest.dto.enums.AuditType;
import jdotest.model.interfaces.IAuditHttpRequestsService;
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

    @Override
    public IAuditHttpRequestsService CreateHttpRequest() {
        IAuditHttpRequestsService ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Audit a = new Audit(AuditType.HttpRequest);
            pm.makePersistent(a);
            Audit requestAudit = pm.detachCopy(a);
            ret = new AuditHttpRequestsService(audit, requestAudit);
        }
        return ret;
    }
}
