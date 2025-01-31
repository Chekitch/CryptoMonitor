package com.cmlcz.cryptomonitor;

public class UserRequestsExitException extends RuntimeException {

    public UserRequestsExitException() {
        super();
    }

    public UserRequestsExitException(String message) {
        super(message);
    }

}
