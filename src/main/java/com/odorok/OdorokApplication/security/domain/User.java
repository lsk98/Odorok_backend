package com.odorok.OdorokApplication.security.domain;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String refresh;
}
