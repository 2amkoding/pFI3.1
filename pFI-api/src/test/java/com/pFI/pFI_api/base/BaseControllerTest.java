package com.pFI.pFI_api.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pFI.pFI_api.config.TestSecurityConfig;
import com.pFI.pFI_api.config.TestControllersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@WebMvcTest
@Import({TestSecurityConfig.class, TestControllersConfig.class})
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String asJsonString(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    protected SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor withUser(String username) {
        return SecurityMockMvcRequestPostProcessors.jwt().jwt(builder ->
                builder.subject(username)
                );
    }

}
