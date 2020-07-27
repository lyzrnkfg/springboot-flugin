package com.nasus.helloword.exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("user not exist");
    }
}
