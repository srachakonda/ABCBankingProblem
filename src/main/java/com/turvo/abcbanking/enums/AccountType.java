package com.turvo.abcbanking.enums;

public enum AccountType {
    PRIORITY((byte) 0),
    REGULAR((byte) 1);

    private byte value;

    private AccountType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}