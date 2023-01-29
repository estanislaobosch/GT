package org.example;

import java.util.Date;

class Person {
    private final String name;

    private final Sex sex;
    private final Date birthdate;

    public Person(String name, Sex sex, Date birthdate) {
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", birthdate=" + birthdate +
                '}';
    }
}
