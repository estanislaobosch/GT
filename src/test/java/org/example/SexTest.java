package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Sex.*;
import static org.junit.jupiter.api.Assertions.*;

class SexTest {

    @Test
    void fromString() {
        assertEquals(FEMALE, Sex.fromString("Female"));
        assertEquals(MALE, Sex.fromString("Male"));
        assertEquals(OTHER, Sex.fromString("whatever"));
    }

    @Test
    void valueOf() {
        assertEquals(FEMALE, Sex.valueOf("FEMALE"));
        assertEquals(MALE, Sex.fromString("MALE"));
        assertEquals(OTHER, Sex.fromString("OTHER"));
    }
}