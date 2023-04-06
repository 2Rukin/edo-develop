package com.education.util;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.http.HttpHost.DEFAULT_SCHEME_NAME;


/**
 * @author Andrey Kryukov
 * Класс помагает билдить URI до сервиса с помощью EurekaClient
 */
@Component
@Log4j2
public class URIBuilderUtil {
    private static EurekaClient eurekaClient;

    /**
     * Билдит динамический URI до сервиса
     *
     * @param service - service name
     * @param path    - uri path
     * @return URIBuilder instance
     */
    public static URIBuilder buildURI(String service, String path) {
        log.info("Get all instances of required service");
        List<InstanceInfo> instances = eurekaClient.getApplication(service).getInstances();
        log.info("Define random instance");
        int randomInstance = ThreadLocalRandom.current().nextInt(instances.size());
        log.info("Get certain instance of service");
        InstanceInfo instance = instances.get(randomInstance);
        log.info("Build uri to service");
        return new URIBuilder()
                    .setScheme(DEFAULT_SCHEME_NAME)
                    .setHost(instance.getHostName())
                    .setPort(instance.getPort())
                    .setPath(path);
    }

    @Autowired
    public void eurekaClientInjector(EurekaClient externalEurekaClient) {
        eurekaClient = externalEurekaClient;
    }
}
