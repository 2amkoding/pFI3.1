package com.pFI.pFI_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
* Configuration class to enable JPA auditing functionality.
* This allows fields marked with @CreatedDate, @LastModifiedDate,
* @CreatedBy, and @LastModifiedBy to be automatically populated.
*/
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // No additional configuration needed
}

