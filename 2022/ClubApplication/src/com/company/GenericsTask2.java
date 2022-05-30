package com.company;

public class GenericsTask2 {

    public static void main(String[] args) {
        System.out.println("Result: " +
                Compute.compute("add", Long.valueOf(1), Long.valueOf(2)));
        System.out.println("Result: " +
                Compute.compute("minus", 2, 1));
        System.out.println("Result: " +
                Compute.compute("mul", 2.0f, 5.5f));
        System.out.println("Result: " +
                Compute.compute("div", 8.0, 0.0));
        System.out.println("Result: " +
                Compute.compute("div", 3, 1));
    }
}

class Compute {
    public static <T extends Number> double compute(String op, T x, T y) {
        double ans = 0;
        switch (op) {
            case "add":
                // if you get the error:
                // The value x.doubleValue() + y.doubleValue() assigned to 'ans' is never used ?
                // rmb to break!! then all considered used.
                // if not only the last case is considered used (since there is a possibility of falling through)
                ans =  x.doubleValue() + y.doubleValue();
                break;
            case "minus":
                ans = x.doubleValue() - y.doubleValue();
                break;
            case "mul":
                ans = x.doubleValue() * y.doubleValue();
                break;
            case "div":
                // 0 or 0.0 both work!
                if (y.doubleValue() == 0) {
                 ans = 0.0;
                } else {
                    ans = x.doubleValue() / y.doubleValue();
                }
                break;
        }

//        // not preferred bc does not return the value!
//        switch (op) {
//            case "add":
//                return x.doubleValue() + y.doubleValue();
//            case "minus":
//                return x.doubleValue() - y.doubleValue();
//            case "mul":
//                return x.doubleValue() * y.doubleValue();
//            case "div":
//                // 0 or 0.0 both work!
//                if (y.doubleValue() == 0) {
//                    return 0.0;
//                }
//                return x.doubleValue() / y.doubleValue();
//        }
        // Todo
        // Implement all 4 operations (add/minus/mul/div)
        // as seen in static main(). For op "div", check for and handle
        // "divide by zero" errors by returning 0.0 for such cases
        return ans;
    }
}


