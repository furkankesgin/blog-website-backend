package com.furkan.blog.shared.security;

import com.furkan.blog.shared.jwt.AccessTokenPayload;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUserService {

    @Data
    @Builder
    public static class CurrentUser {
        private String id;
        @EqualsAndHashCode.Exclude
        private String role;
    }

    private CurrentUser currentUser;

    public void fromAccessTokenPayload(AccessTokenPayload accessTokenPayload) {
        currentUser = CurrentUser.builder().id(accessTokenPayload.getId()).role(accessTokenPayload.getMetadata().role()).build();
    }

    public Optional<CurrentUser> getCurrentUser() {
        return Optional.ofNullable(currentUser);
    }

}
