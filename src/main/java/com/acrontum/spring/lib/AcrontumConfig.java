package com.acrontum.spring.lib;

import com.acrontum.spring.lib.locale.AcrontumLocaleConfig;
import com.acrontum.spring.lib.security.AcrontumWebSecurityConfig;
import java.util.Optional;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

/**
 * The type Acrontum config.
 */
@Configuration
@Import({AcrontumWebSecurityConfig.class, AcrontumLocaleConfig.class})
public class AcrontumConfig {

    /**
     * Acrontum rest template rest template.
     *
     * @param builder the builder
     * @return the rest template
     */
    @Bean(name = "acrontumRestTemplate")
    public RestTemplate acrontumRestTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors((request, body, execution) -> {
                    Optional.of(SecurityContextHolder.getContext().getAuthentication())
                            .map(Authentication::getCredentials)
                            .ifPresent(jwt ->
                                    request.getHeaders().add("Authentication", "Bearer " + jwt));
                    return execution.execute(request, body);
                }).build();
    }
}
