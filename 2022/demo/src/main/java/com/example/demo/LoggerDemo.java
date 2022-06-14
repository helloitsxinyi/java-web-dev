package com.example.demo;

// take note of imports!!
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerDemo {
    // initialize loggerdemo class
    public static final Logger LOGGER = Logger.getLogger(LoggerDemo.class.getName());

    public static void main(String[] args) {
        // Severe, Warning, Log, Fine Finer Finest, CONFIG
        System.out.println("HI IT'S LOGGER");
        // use setLevel to control what messages to be printed.
        // abit wonky, still shows warning & severe for now
        LOGGER.setLevel(Level.ALL);
        LOGGER.config("The DB works! wuhu!");
        LOGGER.fine("yeh it's fine");
        LOGGER.finer("starting to be finer");
        LOGGER.finest("im very fine. fairprice finest");

        // you can only see these 2 in console if default settings.
        // use setLevel to control what messages to be printed.
        LOGGER.warning("stop ah");
        LOGGER.severe("THIS IS VERY SEVERE");
    }
}
