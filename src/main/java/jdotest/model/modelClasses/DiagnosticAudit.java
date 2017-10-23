package jdotest.model.modelClasses;

import java.sql.Timestamp;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Key;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import jdotest.dto.DiagnosticAuditMap;
import jdotest.dto.DiagnosticAuditMapBase;
import jdotest.dto.enums.DiagnosticType;

@PersistenceCapable(table = "AuditsDiagnostics")
public class DiagnosticAudit {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private long Id;

    @Key
    long AuditId;

    @Persistent(customValueStrategy = "timestamp", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
    private Timestamp DateTime;

    @Extension(vendorName = "datanucleus", key = "enum-value-getter", value = "getValue")
    @SuppressWarnings("FieldMayBeFinal")
    private DiagnosticType DiagnosticType;

    @SuppressWarnings("FieldMayBeFinal")
    private String Message;

    public DiagnosticAudit(long auditId, DiagnosticAuditMapBase diagnostic) {
        this.AuditId = auditId;
        this.DiagnosticType = diagnostic.getDiagnosticType();
        this.Message = diagnostic.getMessage();
    }

    public DiagnosticAuditMap toDiagnosticAuditMap() {
        return new DiagnosticAuditMap(Id, DateTime.toInstant(), DiagnosticType, Message);
    }
}
