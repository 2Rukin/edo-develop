package com.education.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Util class to get target and source folder`s directions
 */
@Component
public class MinioUtil {

    /**
     * Bucket`s name
     */
    @Getter
    @Value("${minio.bucket-name}")
    private String myBucketname;
}
