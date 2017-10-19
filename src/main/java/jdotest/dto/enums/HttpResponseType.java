package jdotest.dto.enums;

public enum HttpResponseType {
    Unknown(0);

    private final int value;

    private HttpResponseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
