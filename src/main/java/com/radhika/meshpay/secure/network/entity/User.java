package com.radhika.meshpay.secure.network.entity;

import com.radhika.meshpay.secure.network.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(nullable=false)
    String name;
    @Column(nullable=false, unique=true)
    String email;
    @Column(nullable=false)
    String password;
    @Column(nullable=false)
    double balance=0.0;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    LocalDateTime createdAt;

}
