package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.BadRequestException;

public class InvalidTokenTypeException extends BadRequestException {
    public InvalidTokenTypeException(String message) {
        super(message);
    }
}
