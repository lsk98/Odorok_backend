package com.odorok.OdorokApplication.domain;

import com.odorok.OdorokApplication.security.domain.UserStuff;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String nickname;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;

    @Column(length = 30)
    private String role;

    public User(UserStuff userStuff) {
        this.id = userStuff.getId();
        this.name = userStuff.getName();
        this.nickname = userStuff.getNickname();
        this.email = userStuff.getEmail();
        this.password = userStuff.getPassword();
        this.role = "ROLE_USER";
    }
}