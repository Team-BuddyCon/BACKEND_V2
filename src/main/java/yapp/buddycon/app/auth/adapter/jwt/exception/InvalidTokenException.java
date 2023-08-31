package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.ApplicationException;

public class InvalidTokenException extends ApplicationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
