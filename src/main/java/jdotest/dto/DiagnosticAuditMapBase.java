package jdotest.dto;

import jdotest.dto.enums.DiagnosticType;

public class DiagnosticAuditMapBase {
    private final DiagnosticType DiagnosticType;
    private final String Message;

    public DiagnosticAuditMapBase(DiagnosticType DiagnosticType, String Message) {
        this.DiagnosticType = DiagnosticType;
        this.Message = Message;
    }

    public DiagnosticType getDiagnosticType() {
        return DiagnosticType;
    }

    public String getMessage() {
        return Message;
    }
}
