package com.fatihctn.pigeon.enums;

public enum Status {
    PASSIVE(0),
    ACTIVE(1),
    BANNED(2),
    DELETED(3);

    private int status;

    public int getStatus() {
        return status;
    }

    Status(int status) {
        this.status = status;
    }
}
