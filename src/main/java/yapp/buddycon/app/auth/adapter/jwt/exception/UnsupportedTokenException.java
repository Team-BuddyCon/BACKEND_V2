package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.BadRequestException;

public class UnsupportedTokenException extends BadRequestException {
    public UnsupportedTokenException(String message) {
        super(message);
    }
}
