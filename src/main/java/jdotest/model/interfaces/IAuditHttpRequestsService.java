package jdotest.model.interfaces;

import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;

/**
 *
 * @author crijk
 */
public interface IAuditHttpRequestsService extends IAuditHandlerCommon {
    AuditHttpRequestMap StartHttpRequest(AuditHttpRequestsMapBase httpRequest);
    AuditHttpResponseMap SetHttpResponse(AuditHttpResponseMapBase httpResponse);
}
