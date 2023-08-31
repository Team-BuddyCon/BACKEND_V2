package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.ApplicationException;

public class EmptyTokenException extends ApplicationException {
    public EmptyTokenException(String message) {
        super(message);
    }
}
