package jdotest.model.interfaces;

import javax.jdo.JDOObjectNotFoundException;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditHttpRequestsService {
    AuditHttpRequestMap CreateHttpRequest(AuditHttpRequestsMapBase httpRequest);
    AuditHttpRequestMap GetHttpRequest(long id) throws JDOObjectNotFoundException;
    AuditHttpResponseMap SetHttpResponse(long requestAuditId, AuditHttpResponseMapBase httpResponse) throws JDOObjectNotFoundException;
}
