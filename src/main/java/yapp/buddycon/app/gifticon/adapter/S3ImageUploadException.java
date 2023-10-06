package yapp.buddycon.app.gifticon.adapter;

import yapp.buddycon.app.common.response.ApplicationException;

public class S3ImageUploadException extends ApplicationException {
    public S3ImageUploadException(String message) {
        super(message);
    }
}
