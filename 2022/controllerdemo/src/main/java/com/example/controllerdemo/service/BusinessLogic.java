package com.example.controllerdemo.service;

public interface BusinessLogic {
    // like a contract. an interface is a contract.
    // impl1 could be postgres.
    // impl2 could be mysql.
    // allows 3rd party vendors to do any kind of impl.
    public Double countAllBooks(int choice);
}
