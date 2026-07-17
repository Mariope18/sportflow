package com.sportflow.auth.model;

import com.sportflow.auth.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
