package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.ApplicationException;

public class InvalidTokenTypeException extends ApplicationException {
    public InvalidTokenTypeException(String message) {
        super(message);
    }
}
