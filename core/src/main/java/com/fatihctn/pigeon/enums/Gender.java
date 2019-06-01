package com.fatihctn.pigeon.enums;

public enum Gender {
    NOT_PREFER('-'),
    MALE('M'),
    FEMALE('F');

    private char gender;

    Gender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return this.gender;
    }
}
