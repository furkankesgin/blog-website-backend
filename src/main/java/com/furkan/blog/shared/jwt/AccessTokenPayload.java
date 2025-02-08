package com.furkan.blog.shared.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenPayload {
    @JsonProperty("sub")
    private String id;

    @EqualsAndHashCode.Exclude
    private Metadata metadata;

    public record Metadata(String role) { }
}
