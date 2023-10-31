package yapp.buddycon.app.auth.adapter.jwt.exception;

import yapp.buddycon.app.common.response.BadRequestException;

public class MalformedTokenException extends BadRequestException {
    public MalformedTokenException(String message) {
        super(message);
    }
}
