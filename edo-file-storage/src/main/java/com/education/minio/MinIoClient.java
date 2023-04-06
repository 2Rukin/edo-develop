package com.education.minio;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Creating bean of MinioClient by values from application.yml
 */
@Component
public class MinIoClient {

    /**
     * Set url for server of minio
     */
    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * Set username for server of minio
     */
    @Value("${minio.username}")
    private String username;

    /**
     * Set password for server of minio
     */
    @Value("${minio.password}")
    private String password;


    /**
     * Bean of MinioClient with a parameters
     */
    @Bean
    MinioClient createMinio() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(username, password)
                .build();
    }
}
