package com.company;

import java.util.List;

public class Generics {
    static <T> boolean isContained(T[] arr, T element) {
        for (T currEle: arr) {
            if (currEle.equals(element)) {
                return true;
            }
        }
        return false;
    }


}
