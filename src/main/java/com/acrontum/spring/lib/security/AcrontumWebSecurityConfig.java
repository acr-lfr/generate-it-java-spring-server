package com.acrontum.spring.lib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Acrontum web security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = AcrontumWebSecurityConfig.class)
public class AcrontumWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthEntryPointJwt unauthorizedHandler;

    /**
     * Instantiates a new Acrontum web security config.
     *
     * @param unauthorizedHandler the unauthorized handler
     */
    @Autowired
    public AcrontumWebSecurityConfig(AuthEntryPointJwt unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    /**
     * Authentication jwt token filter auth token filter.
     *
     * @return the auth token filter
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests()
                    .antMatchers("/v3/api-docs*/**", "/swagger*/**")
                    .permitAll()
                    .and()
                .authorizeRequests().antMatchers("**/public/**").permitAll().and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }
}