package jdotest.model.database;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import jdotest.dto.AuditHttpRequestsMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.enums.AuditType;
import jdotest.model.interfaces.IAuditHttpRequestsService;
import jdotest.model.modelClasses.Audit;
import jdotest.model.modelClasses.AuditHttpRequest;

public class AuditHttpRequestsService implements IAuditHttpRequestsService {

    @Override
    public AuditHttpRequestsMap CreateHttpRequest(AuditHttpRequestsMapBase httpRequest) {
        AuditHttpRequestsMap ret;
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
    public AuditHttpRequestsMap GetHttpRequest(long id) throws JDOObjectNotFoundException {
        AuditHttpRequestsMap ret = null;
        try (PersistenceManager pm = DatabaseConfiguration.getPersistenceManager()) {
            AuditHttpRequest audit = pm.getObjectById(AuditHttpRequest.class, id);
            if (audit != null) {
                ret = audit.toAuditHttpRequestsMap();
            }
        }

        return ret;
    }
}
