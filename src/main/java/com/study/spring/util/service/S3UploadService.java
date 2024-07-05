package com.study.spring.util.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.study.spring.domain.Post;
import com.study.spring.util.entity.ImageEntity;
import com.study.spring.util.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // MultipartFile을 File로 변환하는 메소드
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return Optional.of(convFile);
    }

    // 단일 파일을 S3에 업로드하는 메소드
    private String upload(File file, String dirName) {
        String fileName = dirName + "/" + file.getName();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length());

        try {
            amazonS3.putObject(bucket, fileName, file);
        } catch (Exception e) {
            log.error("파일을 S3에 업로드하는 중 오류 발생: {}", e.getMessage());
        }

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    // 파일 리스트를 S3에 업로드하고, URL을 반환하는 메소드
    public List<String> upload(List<MultipartFile> multipartFilelist, String dirName) throws IOException {
        return multipartFilelist.stream()
                .map(file -> {
                    try {
                        File uploadFile = convert(file)
                                .orElseThrow(() -> new IllegalArgumentException("오류: MultipartFile -> File 변환 실패"));
                        String url = upload(uploadFile, dirName);
                        uploadFile.delete();
                        return url;
                    } catch (IOException e) {
                        throw new RuntimeException("파일 업로드 실패", e);
                    }
                })
                .collect(Collectors.toList());
    }
}
