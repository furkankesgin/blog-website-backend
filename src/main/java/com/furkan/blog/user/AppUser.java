package com.furkan.blog.user;

import com.furkan.blog.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
public class AppUser {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
}
