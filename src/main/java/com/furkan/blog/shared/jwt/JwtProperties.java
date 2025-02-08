package com.furkan.blog.shared.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String publicKey;
    private int allowedClockSkewInSeconds;
    private String issuer;
    private String jwksUrl;
}
