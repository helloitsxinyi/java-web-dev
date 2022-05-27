package com.company;


public class PrimeNumber {
    public static void main(String[] args) {
        printPrimeNumber();
    }

    static void printPrimeNumber() {
        for (int n = 5; n <= 10; n++) {
            if (isPrime(n)) {
                System.out.println(n);
            }
        }
    }

    static boolean isPrime(int number) {
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
                return isPrime;
            } else {
                isPrime = true;
            }
        }

        return isPrime;
    }
}


