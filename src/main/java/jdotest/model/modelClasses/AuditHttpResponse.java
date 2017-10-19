package jdotest.model.modelClasses;

import java.sql.Timestamp;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import jdotest.dto.AuditHttpResponseMap;
import jdotest.dto.AuditHttpResponseMapBase;
import jdotest.dto.enums.HttpResponseType;

@PersistenceCapable(table = "AuditsHttpResponses")
public class AuditHttpResponse {

    @PrimaryKey
    long AuditId;
    
    @Persistent(customValueStrategy = "timestamp", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
    private Timestamp DateTime;

    @Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "getValue")
    @SuppressWarnings("FieldMayBeFinal")
    private HttpResponseType ResponseType;

    @SuppressWarnings("FieldMayBeFinal")
    private int StatusCode;

    @SuppressWarnings("FieldMayBeFinal")
    private String Body;

    public AuditHttpResponse(long AuditId, AuditHttpResponseMapBase httpResponse) {
        this.AuditId = AuditId;
        this.ResponseType = httpResponse.getHttpResponseType();
        this.StatusCode = httpResponse.getStatusCode();
        this.Body = httpResponse.getBody();
    }

    public AuditHttpResponseMap toAuditHttpResponseMap() {
        return new AuditHttpResponseMap(AuditId, DateTime.toInstant(), ResponseType, StatusCode, Body);
    }
}
