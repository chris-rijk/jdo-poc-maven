package jdotest.dto.enums;

public enum DiagnosticType {
    Startup(1),
    Authorisation(2);

    private final int value;

    private DiagnosticType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
