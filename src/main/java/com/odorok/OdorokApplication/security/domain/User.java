package com.odorok.OdorokApplication.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String refresh;
}
