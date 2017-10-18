package jdotest.dto;

import java.time.Instant;
import jdotest.dto.enums.AuditType;

public class AuditServiceInstancesMap extends AuditServiceInstancesMapBase {

    private final long AuditId;
    private final Instant CreateDateTime;
    private final AuditType AuditType;

    public AuditServiceInstancesMap(long AuditId, Instant CreateDateTime, AuditType AuditType, String IpAddress, String DockerImage) {
        super(IpAddress, DockerImage);
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
}
