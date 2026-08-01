package com.financedomain.configserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.profiles.active=native",
        "spring.cloud.config.server.native.search-locations=classpath:/config"
})
class ConfigServerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Devrait servir la configuration pour authentication-service")
    void shouldServeAuthenticationServiceConfig() throws Exception {
        mockMvc.perform(get("/authentication-service/default"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("authentication-service"))
                .andExpect(jsonPath("$.propertySources").isArray());
    }

    @Test
    @DisplayName("Devrait servir la configuration pour user-service")
    void shouldServeUserServiceConfig() throws Exception {
        mockMvc.perform(get("/user-service/default"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("user-service"))
                .andExpect(jsonPath("$.propertySources").isArray());
    }

    @Test
    @DisplayName("Devrait servir la configuration pour pricing-service")
    void shouldServePricingServiceConfig() throws Exception {
        mockMvc.perform(get("/pricing-service/default"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pricing-service"))
                .andExpect(jsonPath("$.propertySources").isArray());
    }
}
