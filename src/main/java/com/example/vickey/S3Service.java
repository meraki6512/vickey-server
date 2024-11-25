package com.example.vickey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.access-key-id}") String accessKeyId,
            @Value("${aws.s3.secret-access-key}") String secretAccessKey,
            @Value("${aws.s3.bucket-name}") String bucketName
    ) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path tempFile = Files.createTempFile("upload-", fileName);

        // 파일을 임시 디렉토리에 저장
        file.transferTo(tempFile.toFile());

        // S3에 업로드
        PutObjectResponse response = s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build(),
                tempFile
        );

        // 업로드 완료 후 임시 파일 삭제
        Files.deleteIfExists(tempFile);

        // S3 객체 URL 반환
        return "https://" + bucketName + ".s3." +  "ap-northeast-2" + ".amazonaws.com/" + fileName;
    }
}
