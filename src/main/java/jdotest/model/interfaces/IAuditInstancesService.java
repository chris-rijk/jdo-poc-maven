package jdotest.model.interfaces;

import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditInstancesService {
    AuditServiceInstancesMap CreateInstancesAudit(AuditServiceInstancesMapBase serviceInstance);
}
