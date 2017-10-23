package jdotest.dto;

import java.time.Instant;
import jdotest.dto.enums.DiagnosticType;

public class DiagnosticAuditMap extends DiagnosticAuditMapBase {

    private final long Id;
    private final Instant CreateDateTime;

    public DiagnosticAuditMap(long id, Instant createDateTime, DiagnosticType DiagnosticType, String Message) {
        super(DiagnosticType, Message);
        this.Id = id;
        this.CreateDateTime = createDateTime;
    }

    public long getId() {
        return Id;
    }    
    
    public Instant getCreateDateTime() {
        return CreateDateTime;
    }
}
