package com.education.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Anna Artemyeva
 * В этом обьекте константы для работы с файлами
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {
    public static final String FILE_CONTENT_TYPE = "application/pdf";

    // Size
    public static final Long objectSize = -1L;
    public static final Long partSize = 104857600L;

    // Extensions
    public static final String PNG = "png";
    public static final String JPEG = "jpeg";
    public static final String JPG = "jpg";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String PDF = "pdf";
}
