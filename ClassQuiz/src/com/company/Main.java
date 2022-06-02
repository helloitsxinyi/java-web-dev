package com.company;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>(); intList.add(1);
        intList.add(2);
        intList.add(3);
        System.out.println(sumItems(intList));
        List<Double> doubleList = new ArrayList<>(); doubleList.add(1.1);
        doubleList.add(2.2);
        doubleList.add(3.3);
        System.out.println(sumItems(doubleList));

        int[] arr = {1,2,2,5,3,4,5,5,2};

        findMaxNums(arr);
        sumEvenAndOdd(arr);

        // Q: can T be primitive type? tried int[] but say err. must be type T[]
        // A: Generic type arguments are constrained to extend Object,
        // meaning that they are not compatible with primitive instantiations unless boxing is used, undermining performance.
        System.out.println(maxItems(new Integer[]{1, 2, 5, 4}));
        dateTime();
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


    // need a value in order to add to sum, so T extends Number. can;t just sum T
    static <T extends Number> double sumItems(List<T> list) {
        double sum = 0;
        for (T item : list) {
            sum += item.doubleValue();
        }
        return sum;
    }
    public static <T extends Comparable<T>> T maxItems (T[] arr){
        T maxEle = arr[0];
        for (T ele: arr) {
            // generic type: cannot use greater sign!!!
            // need to use COMPARABLE
            if (ele.compareTo(maxEle) > 0) {
                maxEle = ele;
            }
        }
        return maxEle;
    }

    public static void dateTime() {
        LocalDateTime startTimeInSG = LocalDateTime.of(2020,1,1,8,10);
        ZonedDateTime sgTimeZoneAndTime = ZonedDateTime.of(startTimeInSG, ZoneId.of("Asia/Singapore"));
        System.out.println("Time in SG: " + sgTimeZoneAndTime);

        LocalDateTime endTimeInSG = startTimeInSG.plusHours(18).plusMinutes(25);
        System.out.println(endTimeInSG);
        ZonedDateTime endTimeInNY = ZonedDateTime.of(endTimeInSG, ZoneId.of("America/New_York"));
        System.out.println("Time in NY after landing: " + endTimeInNY);
    }


}
