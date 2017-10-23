package jdotest.model.interfaces;

import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditHttpRequestsService {
    long GetAuditId();
    AuditHttpRequestMap StartHttpRequest(AuditHttpRequestsMapBase httpRequest);
    AuditHttpResponseMap SetHttpResponse(AuditHttpResponseMapBase httpResponse);
}
