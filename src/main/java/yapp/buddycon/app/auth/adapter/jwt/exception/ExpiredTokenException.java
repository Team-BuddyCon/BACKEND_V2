package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.ApplicationException;

public class ExpiredTokenException extends ApplicationException {
    public ExpiredTokenException(String message) {
        super(message);
    }
}
