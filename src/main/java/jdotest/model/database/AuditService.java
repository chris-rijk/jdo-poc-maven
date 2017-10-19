package jdotest.model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import jdotest.dto.enums.NameValuePairType;
import jdotest.model.interfaces.IAuditService;
import jdotest.model.modelClasses.AuditNameValuePair;

public class AuditService implements IAuditService {

    @Override
    public void SetAuditNameValuePairs(long auditId, NameValuePairType dataType, Map<String, String> pairs) {
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Query<AuditNameValuePair> query = pm.newQuery(AuditNameValuePair.class, "AuditId == auditId && DataType == dataType");
            query.parameters("long auditId, int dataType");
            query.deletePersistentAll(auditId, dataType.getValue());
            ArrayList<AuditNameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> pair: pairs.entrySet()) {
                AuditNameValuePair add = new AuditNameValuePair(auditId, dataType, pair.getKey(), pair.getValue());
                list.add(add);
            }
            pm.makePersistentAll(list);
        }
    }

    @Override
    public Map<String, String> GetAuditNameValuePairs(long auditId, NameValuePairType dataType) {
        HashMap<String,String> ret = new HashMap<>();
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Query<AuditNameValuePair> query = pm.newQuery(AuditNameValuePair.class, "AuditId == auditId && DataType == dataType");
            query.parameters("long auditId, int dataType");
            List<AuditNameValuePair> results = (List<AuditNameValuePair>) query.execute(auditId, dataType.getValue());
            for (AuditNameValuePair entry: results) {
                ret.put(entry.getName(), entry.getValue());
            }
        }
        return ret;
    }
}
