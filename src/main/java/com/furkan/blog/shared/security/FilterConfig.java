package com.furkan.blog.shared.security;

import com.furkan.blog.shared.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtUtils jwtUtils;
    private final CurrentUserService currentUserService;

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter(jwtUtils, currentUserService));
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}