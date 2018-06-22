package com.tipico.services.myservice.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers(EndpointRequest.to("health", "info", "metrics", "prometheus"))
                    .permitAll()
            .requestMatchers(EndpointRequest.toAnyEndpoint())
                .fullyAuthenticated()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .csrf()
                    .disable()
                .httpBasic();
    }
}
