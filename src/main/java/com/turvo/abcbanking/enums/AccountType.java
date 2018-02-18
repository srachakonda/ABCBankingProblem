package com.turvo.abcbanking.enums;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
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