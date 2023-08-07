package yapp.buddycon.app.auth.adapter;

import yapp.buddycon.app.common.response.ApplicationException;

public class OAuthRequestException extends ApplicationException {

    public OAuthRequestException(String message) {
        super(message);
    }
}
