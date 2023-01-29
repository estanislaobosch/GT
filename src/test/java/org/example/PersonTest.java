package org.example;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private static final String NAME = "Name";
    private static final Sex SEX = Sex.FEMALE;
    private static final Date DATE;

    static {
        try {
            DATE = new SimpleDateFormat("dd/MM/yyyy").parse("14/02/1969");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Person PERSON = new Person(NAME, SEX, DATE);
    private static final String STR_PERSON = "Person{name='Name', sex=FEMALE, birthdate=Fri Feb 14 00:00:00 GMT 1969}";

    @Test
    void getName() {
        assertEquals(NAME, PERSON.getName());
    }

    @Test
    void getSex() {
        assertEquals(SEX, PERSON.getSex());
    }

    @Test
    void getBirthdate() {
        assertEquals(DATE, PERSON.getBirthdate());
    }

    @Test
    void testToString() {
        assertEquals(STR_PERSON, PERSON.toString());
    }
}