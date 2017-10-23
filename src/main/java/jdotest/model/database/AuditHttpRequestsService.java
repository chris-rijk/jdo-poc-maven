package jdotest.model.database;

import javax.jdo.PersistenceManager;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;
import jdotest.model.interfaces.IAuditHttpRequestsService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditHttpRequest;
import jdotest.model.modelClasses.AuditHttpResponse;

public class AuditHttpRequestsService implements IAuditHttpRequestsService {

    private final Audit audit;
    private final long auditId;

    protected AuditHttpRequestsService(Audit audit) {
        this.audit = audit;
        this.auditId = audit.getId();
    }

    @Override
    public long GetAuditId() {
        return auditId;
    }

    @Override
    public AuditHttpRequestMap StartHttpRequest(AuditHttpRequestsMapBase httpRequest) {
        AuditHttpRequestMap ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditHttpRequest req = new AuditHttpRequest(auditId, httpRequest);
            pm.makePersistent(req);
            ret = req.toAuditHttpRequestsMap(audit);
        }

        return ret;
    }

    @Override
    public AuditHttpResponseMap SetHttpResponse(AuditHttpResponseMapBase httpResponse) {
        AuditHttpResponseMap ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditHttpResponse response = new AuditHttpResponse(auditId, httpResponse);
            pm.makePersistent(response);
            ret = response.toAuditHttpResponseMap();
        }

        return ret;
    }
}
