package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.BadRequestException;

public class ExpiredTokenException extends BadRequestException {
    public ExpiredTokenException(String message) {
        super(message);
    }
}
