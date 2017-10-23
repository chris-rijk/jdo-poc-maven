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
import jdotest.dto.enums.AuditType;
import jdotest.dto.enums.DiagnosticType;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditHttpRequestsService;
import jdotest.model.interfaces.IAuditInstancesService;
import jdotest.model.interfaces.IAuditService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditHttpRequest;
import jdotest.model.modelClasses.AuditNameValuePair;
import jdotest.model.modelClasses.AuditServiceInstance;

public class AuditService implements IAuditService {

    @Override
    public IAuditInstancesService CreateInstancesAudit() {
        Audit audit = CreateAuditInstance(AuditType.ServiceInstance);
        return new AuditInstancesService(audit);
    }

    @Override
    public IAuditHttpRequestsService CreateHttpRequest() {
        Audit audit = CreateAuditInstance(AuditType.HttpRequest);
        return new AuditHttpRequestsService(audit);
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
    public void SetAuditNameValuePairs(long auditId, NameValuePairType dataType, Map<String, String> pairs) {
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Query<AuditNameValuePair> query = pm.newQuery(AuditNameValuePair.class, "AuditId == auditId && DataType == dataType");
            query.parameters("long auditId, int dataType");
            query.deletePersistentAll(auditId, dataType.getValue());
            ArrayList<AuditNameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> pair : pairs.entrySet()) {
                AuditNameValuePair add = new AuditNameValuePair(auditId, dataType, pair.getKey(), pair.getValue());
                list.add(add);
            }
            pm.makePersistentAll(list);
        }
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
    public void AuditDiagnostics(long auditId, DiagnosticType diagnosticType, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
