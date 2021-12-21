package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // need a value in order to add to sum, so T extends Number. can;t just sum T
    static <T extends Number> double sumItems(List<T> list) {
        double sum = 0;
        for (T item : list) {
            sum += item.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>(); intList.add(1);
        intList.add(2);
        intList.add(3); System.out.println(sumItems(intList));
        List<Double> doubleList = new ArrayList<>(); doubleList.add(1.1);
        doubleList.add(2.2);
        doubleList.add(3.3); System.out.println(sumItems(doubleList));
    }
}
