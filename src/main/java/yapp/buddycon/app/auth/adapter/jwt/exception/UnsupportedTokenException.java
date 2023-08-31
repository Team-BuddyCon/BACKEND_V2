package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.ApplicationException;

public class UnsupportedTokenException extends ApplicationException {
    public UnsupportedTokenException(String message) {
        super(message);
    }
}
