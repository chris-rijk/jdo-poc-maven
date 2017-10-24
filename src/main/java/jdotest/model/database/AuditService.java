package jdotest.model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.DiagnosticAuditMap;
import jdotest.dto.enums.AuditType;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.interfaces.IAuditService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditHttpRequest;
import jdotest.model.modelClasses.AuditNameValuePair;
import jdotest.model.modelClasses.AuditServiceInstance;
import jdotest.model.modelClasses.DiagnosticAudit;

public class AuditService implements IAuditService {

    @Override
    public IAuditInstancesService CreateInstancesAudit() {
        Audit audit = CreateAuditInstance(AuditType.ServiceInstance);
        return new AuditInstancesService(audit);
    }

    private Audit CreateAuditInstance(AuditType type) {
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Audit a = new Audit(type);
            pm.makePersistent(a);
            return pm.detachCopy(a);
        }
    }

    @Override
    public AuditHttpRequestMap GetHttpRequest(long auditId) throws JDOObjectNotFoundException {
        AuditHttpRequestMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Audit audit = pm.getObjectById(Audit.class, auditId);
            if (audit != null && audit.getAuditType() == AuditType.HttpRequest) {
                AuditHttpRequest request = pm.getObjectById(AuditHttpRequest.class, auditId);
                if (request != null) {
                    ret = request.toAuditHttpRequestsMap(audit);
                }
            }
        }

        return ret;
    }

    @Override
    public AuditServiceInstancesMap GetInstancesAudit(long auditId) throws JDOObjectNotFoundException {
        AuditServiceInstancesMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Audit audit = pm.getObjectById(Audit.class, auditId);
            if (audit != null && audit.getAuditType() == AuditType.ServiceInstance) {
                AuditServiceInstance service = pm.getObjectById(AuditServiceInstance.class, auditId);
                if (service != null) {
                    ret = service.toAuditServiceInstancesMap(audit);
                }
            }
        }

        return ret;
    }

    @Override
    public Map<String, String> GetAuditNameValuePairs(long auditId, NameValuePairType dataType) {
        HashMap<String, String> ret = new HashMap<>();
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Query<AuditNameValuePair> query = pm.newQuery(AuditNameValuePair.class, "AuditId == auditId && DataType == dataType");
            query.parameters("long auditId, int dataType");
            List<AuditNameValuePair> results = (List<AuditNameValuePair>) query.execute(auditId, dataType.getValue());
            for (AuditNameValuePair entry : results) {
                ret.put(entry.getName(), entry.getValue());
            }
        }
        return ret;
    }

    @Override
    public List<DiagnosticAuditMap> GetDiagnosticAudits(long auditId) {
        List<DiagnosticAuditMap> ret = new ArrayList<>();
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Query<DiagnosticAudit> query = pm.newQuery(DiagnosticAudit.class, "AuditId == auditId");
            query.parameters("long auditId");
            query.orderBy("DateTime");
            List<DiagnosticAudit> results = (List<DiagnosticAudit>) query.execute(auditId);
            for (DiagnosticAudit entry : results) {
                ret.add(entry.toDiagnosticAuditMap());
            }
        }
        return ret;
    }
}
