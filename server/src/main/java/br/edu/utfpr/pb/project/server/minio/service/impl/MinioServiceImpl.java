package br.edu.utfpr.pb.project.server.minio.service.impl;
import br.edu.utfpr.pb.project.server.minio.config.MinioConfig;
import br.edu.utfpr.pb.project.server.minio.payload.FileResponse;
import br.edu.utfpr.pb.project.server.minio.service.MinioService;
import br.edu.utfpr.pb.project.server.minio.util.MinioUtil;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public  class MinioServiceImpl implements MinioService {
    private  final  MinioUtil minioUtil;
    private  final  MinioConfig minioProperties;

    public MinioServiceImpl(MinioUtil minioUtil, MinioConfig minioProperties) {
        this.minioUtil = minioUtil;
        this.minioProperties = minioProperties;
    }

    @SneakyThrows
    @Override
    public FileResponse putObject(byte[] bytes, String objectName, String contentType, String bucketName) {
        bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
        if (!this.bucketExists(bucketName)) {
            this.makeBucket(bucketName);
            minioUtil.setBucketPolicyToPublicReadOnly(bucketName);
        }

        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            minioUtil.putObject(bucketName, objectName, inputStream, contentType);

            return FileResponse.builder()
                    .filename(objectName)
                    .fileSize((long) bytes.length)
                    .contentType(contentType)
                    .createdTime(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Falha no upload do arquivo: " + e.getMessage(), e);
        }
    }

    @SneakyThrows
    @Override
    public  FileResponse putObject(MultipartFile multipartFile, String bucketName, String fileType) {
        try {
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
                minioUtil.setBucketPolicyToPublicReadOnly(bucketName);
            }
            minioUtil.setBucketPolicyToPublicReadOnly(bucketName);
            String fileName = multipartFile.getOriginalFilename();
            Long fileSize = multipartFile.getSize();
            String objectName = UUID.randomUUID().toString().replaceAll("-", "") + fileName.substring(fileName.lastIndexOf("."));
            LocalDateTime createdTime = LocalDateTime.now();
            minioUtil.putObject(bucketName, multipartFile, objectName,fileType);
            return FileResponse.builder().filename(objectName).fileSize(fileSize)
                    .contentType(fileType).createdTime(createdTime)
                    .build();
        } catch (Exception e) {
            return  null;
        }
    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        String finalBucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioProperties.getBucketName();
        return minioUtil.getObject(finalBucketName, objectName);
    }

    @Override
    public  boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }
    @Override
    public  void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }
    @Override
    public  List<String> listBucketName() {
        return minioUtil.listBucketNames();
    }
    @Override
    public  List<Bucket> listBuckets() {
        return minioUtil.listBuckets();
    }
    @Override
    public  boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }
    @Override
    public  List<String> listObjectNames(String bucketName) {
        return minioUtil.listObjectNames(bucketName);
    }
    @Override
    public  boolean removeObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }
    @Override
    public  boolean removeListObject(String bucketName, List<String> objectNameList) {
        return minioUtil.removeObject(bucketName,objectNameList);
    }
    @Override
    public  String getObjectUrl(String bucketName, String objectName) {
        return minioUtil.getObjectUrl(bucketName, objectName);
    }
}