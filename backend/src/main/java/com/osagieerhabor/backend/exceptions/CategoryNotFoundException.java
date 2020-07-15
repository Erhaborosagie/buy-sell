package com.osagieerhabor.backend.exceptions;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(String s) {
        super(s);
    }
}
