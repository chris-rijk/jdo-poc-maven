package jdotest.dto.enums;

public enum HttpRequestType {
    Unknown(0),
    ComapanyGet(1);

    private final int value;

    private HttpRequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
