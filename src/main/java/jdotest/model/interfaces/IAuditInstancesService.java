package jdotest.model.interfaces;

import jdotest.dto.AuditServiceInstancesMap;

/**
 *
 * @author crijk
 */
public interface IAuditInstancesService {
    AuditServiceInstancesMap CreateInstancesAudit(String ipAdress, String dockerImage);
}
