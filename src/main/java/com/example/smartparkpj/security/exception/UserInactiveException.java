package com.example.smartparkpj.security.exception;

import javax.security.sasl.AuthenticationException;

public class UserInactiveException extends AuthenticationException {

    public UserInactiveException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserInactiveException(String msg) {
        super(msg);
    }
}
