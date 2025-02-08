package com.furkan.blog.shared.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtilsImpl implements JwtUtils {

    private final JwtProperties jwtProperties;
    private final ObjectMapper objectMapper;

    @Override
    public <T> Optional<T> decodeToken(String token, Class<T> clazz) {

        var key = getPublicKey();

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKey(key.orElseThrow(RuntimeException::new).getKey())
                .setAllowedClockSkewInSeconds(jwtProperties.getAllowedClockSkewInSeconds())
                .setExpectedIssuer(jwtProperties.getIssuer())
                .setSkipDefaultAudienceValidation()
                .setRequireExpirationTime()
                .setRequireSubject()
                .build();

        try {
            var claims = jwtConsumer.processToClaims(token).getClaimsMap();
            T obj = objectMapper.convertValue(claims, clazz);
            return Optional.of(obj);
        } catch (InvalidJwtException e) {
            if (e.hasExpired()) {
                log.warn("JWT expired");
            } else if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                log.warn("JWT had wrong audience");
            } else {
                log.warn(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }

        return Optional.empty();
    }

    private Optional<JsonWebKey> getPublicKey() {
        try {
            URI jwksUri = new URI(jwtProperties.getJwksUrl());
            String jwksJson = new Scanner(jwksUri.toURL().openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
            JsonWebKeySet jwks = new JsonWebKeySet(jwksJson);
            return Optional.of(jwks.getJsonWebKeys().getFirst());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Optional.empty();
        }
    }

}