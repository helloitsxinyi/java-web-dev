package com.company;

import java.util.stream.IntStream;

public class Streams {

    IntStream.range(1,100).filter(x -> x % 2 == 0).forEach(x -> System.out.print())

}
