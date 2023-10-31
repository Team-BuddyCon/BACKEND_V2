package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.BadRequestException;

public class EmptyTokenException extends BadRequestException {
    public EmptyTokenException(String message) {
        super(message);
    }
}
