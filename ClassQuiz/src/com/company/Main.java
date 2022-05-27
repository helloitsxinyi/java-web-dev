package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

        int[] arr = {1,2,2,5,3,4,5,5,2};
        findMaxNums(arr);
        sumEvenAndOdd(arr);
    }

    public static void findMaxNums(int[] numArr) {
        HashMap<Integer, Integer> numFreqs = new HashMap<>();
        int maxVal = 0;
        int count = 0;

        for (int i = 0; i < numArr.length; i++) {
            if (numArr[i] > maxVal) {
            maxVal = i;
            count = 1;
            }
            else if (numArr[i] == maxVal) {
                count += 1;
            }
        }
        System.out.println("max val is " + maxVal + " (count: " + count + ")" );
    }

    public static void sumEvenAndOdd(int[] arr) {
        int evenSum = 0;
        int oddSum = 0;

        for (int i: arr) {
            if (i % 2 == 0) {
                evenSum += i;
            }
            else {
                oddSum += i;
            }
        }

        System.out.println("even sum is: " +  evenSum + " and odd sum is " + oddSum);
    }
}
