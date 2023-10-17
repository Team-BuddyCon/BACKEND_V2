package yapp.buddycon.app.common.s3.adapter;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import yapp.buddycon.app.common.s3.application.port.out.ImageUploader;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Transactional
public class S3ImageUploader implements ImageUploader {

    private final AmazonS3Client client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
            client.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        } catch (IOException exception) {
            throw new S3ImageUploadException(exception.getMessage());
        }

        return client.getUrl(bucket, originalFilename).toString();
    }
}
