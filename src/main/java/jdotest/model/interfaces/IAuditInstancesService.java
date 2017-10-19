package jdotest.model.interfaces;

import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.AuditServiceInstancesMap;
import jdotest.dto.AuditServiceInstancesMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditInstancesService {
    AuditServiceInstancesMap CreateInstancesAudit(AuditServiceInstancesMapBase serviceInstance);
    AuditServiceInstancesMap GetInstancesAudit(long id) throws JDOObjectNotFoundException;
}
