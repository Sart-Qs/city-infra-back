package com.back.city.services;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinIoService {

    private final MinioClient minioClient;
    private final String bucketName = "city-infra";

    public List<String> uploadFile(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            try {
                //TODO Добавть уникальный префикс
                String objectName = file.getOriginalFilename();

                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());

                String url = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucketName)
                                .object(objectName)
                                .build()
                );

                urls.add(url);
                log.info("Файл загружен: {} → {}", file.getOriginalFilename(), url);

            } catch (Exception e) {
                log.error("Ошибка загрузки файла " + file.getOriginalFilename(), e);
                throw new RuntimeException("Не удалось загрузить файл", e);
            }
        }

        return urls;
    }
}