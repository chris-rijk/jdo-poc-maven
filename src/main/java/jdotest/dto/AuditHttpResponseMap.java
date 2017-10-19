package jdotest.dto;

import java.time.Instant;
import jdotest.dto.enums.HttpResponseType;

public class AuditHttpResponseMap extends AuditHttpResponseMapBase {
    private final long requestAuditId;
    private final Instant responseTime;
    
    public AuditHttpResponseMap(long requestAuditId, Instant responseTime, HttpResponseType httpResponseType, int statusCode, String body) {
        super(httpResponseType, statusCode, body);
        this.requestAuditId = requestAuditId;
        this.responseTime = responseTime;
    }

    public long getRequestAuditId() {
        return requestAuditId;
    }

    public Instant getResponseTime() {
        return responseTime;
    }
}
