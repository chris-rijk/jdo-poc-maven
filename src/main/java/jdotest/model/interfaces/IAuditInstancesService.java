package jdotest.model.interfaces;

import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditInstancesService extends IAuditHandlerCommon {
    AuditServiceInstancesMap StartInstancesAudit(AuditServiceInstancesMapBase serviceInstance);
    IAuditHttpRequestsService CreateHttpRequest();
}
