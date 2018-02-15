package com.turvo.abcbanking.enums;

public enum ServicesOffered {
    WITHDRAW("withdraw"),
    DEPOSIT("deposit"),
    ACC_OPENING("new_acc"),
    ENQUIRY("enquiry");

    private final String serviceOpted;

    private ServicesOffered(final String serviceOpted) {
        this.serviceOpted = serviceOpted;
    }

    @Override
    public String toString() {
        return serviceOpted;
    }
}
