package yapp.buddycon.app.common.s3.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    String upload(MultipartFile multipartFile);
}
