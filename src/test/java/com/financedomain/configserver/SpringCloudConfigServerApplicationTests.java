package com.financedomain.configserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
        "spring.profiles.active=native",
        "spring.cloud.config.server.native.search-locations=classpath:/config"
})
class SpringCloudConfigServerApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Vérifie le chargement du contexte Spring IoC du Config Server")
    void contextLoads() {
        assertNotNull(applicationContext, "Le contexte Spring du Config Server doit s'initialiser correctement.");
        assertThat(applicationContext.containsBean("springCloudConfigServerApplication")).isTrue();
    }

}
