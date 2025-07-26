package com.odorok.OdorokApplication.security.domain;

import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStuff {
    private long id;
    private String name;
    private String nickname;
    private String email;
    private String password;

    public UserStuff(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
