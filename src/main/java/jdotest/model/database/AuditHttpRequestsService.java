package jdotest.model.database;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;
import jdotest.dto.enums.AuditType;
import jdotest.model.interfaces.IAuditHttpRequestsService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditHttpRequest;
import jdotest.model.modelClasses.AuditHttpResponse;

public class AuditHttpRequestsService implements IAuditHttpRequestsService {

    @Override
    public AuditHttpRequestMap CreateHttpRequest(AuditHttpRequestsMapBase httpRequest) {
        AuditHttpRequestMap ret;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            Audit a = new Audit(AuditType.HttpRequest);
            pm.makePersistent(a);
            AuditHttpRequest req = new AuditHttpRequest(a, httpRequest);
            pm.makePersistent(req);
            ret = req.toAuditHttpRequestsMap();
        }

        return ret;
    }

    @Override
    public AuditHttpRequestMap GetHttpRequest(long id) throws JDOObjectNotFoundException {
        AuditHttpRequestMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditHttpRequest audit = pm.getObjectById(AuditHttpRequest.class, id);
            if (audit != null) {
                ret = audit.toAuditHttpRequestsMap();
            }
        }

        return ret;
    }

    @Override
    public AuditHttpResponseMap SetHttpResponse(long requestAuditId, AuditHttpResponseMapBase httpResponse) throws JDOObjectNotFoundException {
        AuditHttpResponseMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditHttpRequest audit = pm.getObjectById(AuditHttpRequest.class, requestAuditId);
            if (audit != null) {
                AuditHttpResponse response = new AuditHttpResponse(requestAuditId, httpResponse);
                pm.makePersistent(response);
                ret = response.toAuditHttpResponseMap();
            }
        }

        return ret;
    }
}
