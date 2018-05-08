package com.assignment.todoapp.exception;

/**
 * Created by Mussadiq on 5/8/2018.
 */
public class ValidationException extends Exception {

    public ValidationException(String error){
        super(error);
    }
}
