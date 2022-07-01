package com.example.controllerdemo.validator;

import com.example.controllerdemo.book.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {
    // need to pass in the class of whose instances you'd like to validate.
    // Can this Validator validate instances of the supplied Class?
    // What is passed to supports() is the class of the object.
    @Override
    public boolean supports(Class<?> c) {
        return Book.class.isAssignableFrom(c);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book b = (Book) target;
        // business validation
        ValidationUtils.rejectIfEmpty(errors,"name","Book name is required!");
    }
}
