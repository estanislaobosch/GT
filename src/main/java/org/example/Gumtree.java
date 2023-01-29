package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Gumtree {
    private static final String NAME1 = "Bill";
    private static final String NAME2 = "Paul";
    private static final long MILLIS_TO_DAYS_FACTOR = 1000L*60L*60L*24L;

    protected List<Person> listings;
    public static void main(String[] args) throws IOException {
        Gumtree gumtree = new Gumtree();
        gumtree.readInputFile("src/main/resources/AddressBook");
        long males = gumtree.numberOfMales();
        System.out.printf("There are %s males in the address book%n", males);
        Optional<Person> min = gumtree.oldestPerson();
        if (min.isPresent()) {
            System.out.printf("The oldest person in the address book is %s%n", min);
        } else {
            System.out.println("There is no oldest person in an empty address book");
        }
        long daysOlder = gumtree.daysOlder(NAME1, NAME2);
        System.out.printf("%s is %s days older than %s%n", NAME1, daysOlder, NAME2);
    }

    protected long numberOfMales() {
        return listings.stream().filter(l -> l.getSex() == Sex.MALE).count();
    }

    protected Optional<Person> oldestPerson() {
        return listings.stream().min(Comparator.comparing(Person::getBirthdate));
    }

    protected long daysOlder(String name1, String name2) {
        Optional<Person> person1 = findByFirstName(name1);
        if (person1.isEmpty()) throw new NoSuchElementException();
        Optional<Person> person2 = findByFirstName(name2);
        if (person2.isEmpty()) throw new NoSuchElementException();
        long time1 = person1.get().getBirthdate().getTime();
        long time2 = person2.get().getBirthdate().getTime();
        long days1 = time1 / MILLIS_TO_DAYS_FACTOR;
        long days2 = time2 / MILLIS_TO_DAYS_FACTOR;
        return days2 - days1;
    }

    protected Optional<Person> findByFirstName(String name) {
        return listings.stream().filter(p -> p.getName().startsWith(name)).findFirst();
    }

    protected void readInputFile(String fileName) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        listings = br.lines().map(s -> {
            String[] strListing = s.split(",");
            try {
                return new Person(strListing[0].trim(), Sex.fromString(strListing[1].trim()), df.parse(strListing[2].trim()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
