package yapp.buddycon.app.gifticon.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    String upload(MultipartFile multipartFile);
}
