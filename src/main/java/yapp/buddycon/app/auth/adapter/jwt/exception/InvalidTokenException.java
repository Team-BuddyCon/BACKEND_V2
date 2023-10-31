package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.BadRequestException;

public class InvalidTokenException extends BadRequestException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
