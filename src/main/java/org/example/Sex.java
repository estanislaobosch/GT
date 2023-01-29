package org.example;

enum Sex {
    MALE,
    FEMALE,
    OTHER;

    public static Sex fromString(String sex) {
        if ("MALE".equalsIgnoreCase(sex)) return MALE;
        if ("FEMALE".equalsIgnoreCase(sex)) return FEMALE;
        return OTHER;
    }
}
