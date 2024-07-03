package com.study.spring.util.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile) throws IOException {

        //업로드 된 파일의 원본 파일 이름을 originalFilename 변수에 저장
        String originalFilename = multipartFile.getOriginalFilename();

        //ObjectMetadata : S3 객체 메타 데이터를 설정하는 클래스
        ObjectMetadata metadata = new ObjectMetadata();
        //업로드 된 파일의 크기를 메타데이터에 설정
        metadata.setContentLength(multipartFile.getSize());
        //업로드 된 파일의 MIME타입을 메타데이터에 설정
        //MIME((Multipurpose Internet Mail Extensions) 타입
        // HTML 문서 : 'text/html'
        // JPEG : 'image/jpeg'
        // PDF : 'application/pdf'
        // 이 정보를 multipartFile.getContentType() 메소드를 통해 얻을 수 있음
        // 이를 통해 파일이 어떤 종류의 파일인지 알 수 있고 이를 적절하게 처리할 수 있음
        metadata.setContentType(multipartFile.getContentType());

        //AmazonS3 클라이언트를 사용하여 파일을 s3 버킷에 업로드
        // bucket : 파일을 저장할 S3 버킷이름
        // originalFilename : S3에 저장할 파일의 이름
        // multipartFile.getInputStream() : 파일의 내용을 읽을 수 있는 입력 스트림
        // metadata : 파일의 메타 데이터
        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
}




