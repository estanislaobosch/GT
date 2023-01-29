package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GumtreeTest {
    private static final long MILLIS_TO_DAYS_FACTOR = 1000L*60L*60L*24L;
    private static final Person PERSON1 = new Person("FirstName1 LastName1", Sex.FEMALE, new Date(0));
    private static final Person PERSON2 = new Person("FirstName2 LastName2", Sex.MALE, new Date(10 * MILLIS_TO_DAYS_FACTOR));

    private Gumtree gumtree;
    private List<Person> listings;

    @BeforeEach
    public void setup() {
        gumtree = new Gumtree();
        listings = new ArrayList<>();
        gumtree.listings = listings;
    }

    @Test
    void numberOfMales() {
        assertEquals(0, gumtree.numberOfMales(), "empty listings");
        listings.add(PERSON1);
        assertEquals(0, gumtree.numberOfMales(), "1 female");
        listings.add(PERSON2);
        assertEquals(1, gumtree.numberOfMales(), "1 male");
    }

    @Test
    void oldestPerson() {
        listings.add(PERSON1);
        listings.add(PERSON2);
        assertTrue(gumtree.oldestPerson().isPresent());
        assertEquals(PERSON1, gumtree.oldestPerson().get());
    }

    @Test
    void oldestPersonWithEmptyListings() {
        assertTrue(gumtree.oldestPerson().isEmpty());
    }

    @Test
    void daysOlder() {
        listings.add(PERSON1);
        listings.add(PERSON2);
        assertEquals(10, gumtree.daysOlder("FirstName1", "FirstName2"));
    }

    @Test
    void findByFirstNameWithPersonNotInListing() {
        listings.add(PERSON1);
        listings.add(PERSON2);
        assertTrue(gumtree.findByFirstName("FirtName1").isEmpty());
    }

    @Test
    void findByFirstName() {
        listings.add(PERSON1);
        listings.add(PERSON2);
        assertTrue(gumtree.findByFirstName("FirstName2").isPresent());
        assertEquals(PERSON2, gumtree.findByFirstName("FirstName2").get());
    }

    @Test
    void readInputFile() throws IOException {
        gumtree.readInputFile("src/test/resources/AddressBook");
        assertEquals(1, gumtree.listings.size());
    }

    @Test
    void readInputFileWithDateError() {
        assertThrows(RuntimeException.class, () -> gumtree.readInputFile("src/test/resources/ErrorAddressBook"));
    }
}