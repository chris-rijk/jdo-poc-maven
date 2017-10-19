package jdotest.dto;

import java.time.Instant;
import java.util.Objects;
import jdotest.dto.enums.AuditType;
import jdotest.dto.enums.HttpRequestSourceType;
import jdotest.dto.enums.HttpRequestType;

public class AuditHttpRequestsMap extends AuditHttpRequestsMapBase {

    private final long AuditId;
    private final Instant CreateDateTime;
    private final AuditType AuditType;

    public AuditHttpRequestsMap(long AuditId, Instant CreateDateTime, AuditType AuditType, long ServiceInstanceId, String url, String body, HttpRequestType requestType, HttpRequestSourceType requestSourceType) {
        super(ServiceInstanceId, url, body, requestType, requestSourceType);
        this.AuditId = AuditId;
        this.CreateDateTime = CreateDateTime;
        this.AuditType = AuditType;
    }

    public long getAuditId() {
        return AuditId;
    }

    public Instant getCreateDateTime() {
        return CreateDateTime;
    }

    public AuditType getAuditType() {
        return AuditType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.AuditId ^ (this.AuditId >>> 32));
        hash = 37 * hash + Objects.hashCode(this.CreateDateTime);
        hash = 37 * hash + Objects.hashCode(this.AuditType);
        hash = 37 * hash + super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuditHttpRequestsMap other = (AuditHttpRequestsMap) obj;
        if (this.AuditId != other.AuditId) {
            return false;
        }
        if (!Objects.equals(this.CreateDateTime, other.CreateDateTime)) {
            return false;
        }
        if (this.AuditType != other.AuditType) {
            return false;
        }
        return super.equals(obj);
    }
    
    
}
