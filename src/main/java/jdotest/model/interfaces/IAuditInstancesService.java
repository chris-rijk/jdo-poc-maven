package jdotest.model.interfaces;

import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditInstancesService {
    long GetAuditId();
    AuditServiceInstancesMap StartInstancesAudit(AuditServiceInstancesMapBase serviceInstance);
}
