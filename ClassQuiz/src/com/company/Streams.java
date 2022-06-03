package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Streams {

//    IntStream.range(1,100).filter(x -> x % 2 == 0).forEach(x -> System.out.print())

    // calculate product of all elements
    double[] arr = { 1.1, 2.2, 3.3 };
    double product = DoubleStream.of(arr).reduce(1,(subtotal, e)->subtotal*e);

    public static List<Person> generatePersonList() {
        List<Person> l = new ArrayList<>();
        l.add(new Person("John", "Tan", 32, 2));
        l.add(new Person("Jessica", "Lim", 28, 3));
        l.add(new Person("Mary", "Lee", 42, 2));
        l.add(new Person("Jason", "Ng", 33, 1));
        l.add(new Person("Mike", "Ong", 22, 0));
        return l;
    }

    List<Person> persons = generatePersonList();

    Person pMostKids = persons.stream().max((p1, p2) -> {
        if (p1.getKids() > p2.getKids()) {
            // Bad return type in lambda expression: Person cannot be converted to int
            return p1;
        }
        return p2;
    }).get();
}
