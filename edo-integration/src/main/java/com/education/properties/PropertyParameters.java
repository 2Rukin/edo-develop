package com.education.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Kryukov Andrey
 * Здест храняться переменные из application
 */
@Component
@Getter
public class PropertyParameters {

    @Value("${spring.mail.username}")
    private String emailUsername;

}
