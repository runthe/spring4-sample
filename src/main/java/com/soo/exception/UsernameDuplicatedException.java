package com.soo.exception;

/**
 * @author KHS
 */
public class UsernameDuplicatedException extends RuntimeException {
    public UsernameDuplicatedException(String name) {
        super(name + " is duplicated");
    }
}
