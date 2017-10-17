package jdotest.model.enums;

/**
 *
 * @author crijk
 */
public enum CompaniesOptionalDataType {
    Platform(1);
    
    private final int value;
    private CompaniesOptionalDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
