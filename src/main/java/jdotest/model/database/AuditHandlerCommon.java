package jdotest.model.database;

import java.util.ArrayList;
import java.util.Map;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditHandlerCommon;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditNameValuePair;
import jdotest.model.modelClasses.DiagnosticAudit;

public class AuditHandlerCommon implements IAuditHandlerCommon {
    protected final Audit audit;
    protected final long auditId;

    protected AuditHandlerCommon(Audit audit) {
        this.audit = audit;
        this.auditId = audit.getId();
    }

    @Override
    public long GetAuditId() {
        return auditId;
    }

    @Override
    public void SetAuditNameValuePairs(NameValuePairType dataType, Map<String, String> pairs) {
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
    public void AuditDiagnostics(DiagnosticAuditMapBase diagnostic) {
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            DiagnosticAudit da = new DiagnosticAudit(auditId, diagnostic);
            pm.makePersistent(da);
        }
    }
}
