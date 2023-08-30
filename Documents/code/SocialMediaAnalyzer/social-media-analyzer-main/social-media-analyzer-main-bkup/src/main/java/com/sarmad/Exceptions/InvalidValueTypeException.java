package com.sarmad.Exceptions;

public class InvalidValueTypeException extends Exception {
    public InvalidValueTypeException(String message) {
        super("Invalid value type entered, please enter interger value and try again.");
    }
}
