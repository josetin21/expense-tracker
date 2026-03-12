package com.josetin.expense_tracker.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public  UserAlreadyExistsException(String message){
        super(message);
    }
}
