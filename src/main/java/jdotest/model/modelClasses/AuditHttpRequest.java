package jdotest.model.modelClasses;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import jdotest.dto.AuditHttpRequestMap;
import jdotest.dto.AuditHttpRequestsMapBase;
import jdotest.dto.enums.HttpRequestSourceType;
import jdotest.dto.enums.HttpRequestType;

@PersistenceCapable(table = "AuditsHttpRequests")
public class AuditHttpRequest {
    
    @PrimaryKey
    long AuditId;

    @SuppressWarnings("FieldMayBeFinal")
    private long ServiceInstanceId;
    
    @SuppressWarnings("FieldMayBeFinal")
    private String url;

    @SuppressWarnings("FieldMayBeFinal")
    private String body;

    @Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "getValue")
    @SuppressWarnings("FieldMayBeFinal")
    private HttpRequestType requestType;

    @Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "getValue")
    @SuppressWarnings("FieldMayBeFinal")
    private HttpRequestSourceType requestSourceType;
   
    public AuditHttpRequest(long auditId, AuditHttpRequestsMapBase httpRequest) {
        this.AuditId = auditId;
        this.ServiceInstanceId = httpRequest.getServiceInstanceId();
        this.url = httpRequest.getURI().toString();
        this.body = httpRequest.getBody();
        this.requestType = httpRequest.getRequestType();
        this.requestSourceType = httpRequest.getRequestSourceType();
    }

    public AuditHttpRequestMap toAuditHttpRequestsMap(Audit audit) {
        return new AuditHttpRequestMap(audit.getId(), audit.getCreateDateTime(), audit.getAuditType(), ServiceInstanceId, url, body, requestType, requestSourceType);
    }
}
