package yapp.buddycon.app.auth.adapter.oauth;

import yapp.buddycon.app.common.response.BadRequestException;

public class OAuthRequestException extends BadRequestException {

    public OAuthRequestException(String message) {
        super(message);
    }
}
