package jdotest.model.interfaces;

import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.AuditHttpRequestsMap;
import jdotest.dto.AuditHttpRequestsMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditHttpRequestsService {
    AuditHttpRequestsMap CreateHttpRequest(AuditHttpRequestsMapBase httpRequest);
    AuditHttpRequestsMap GetHttpRequest(long id) throws JDOObjectNotFoundException;
}
