package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.ApplicationException;

public class MalformedTokenException extends ApplicationException {
    public MalformedTokenException(String message) {
        super(message);
    }
}
