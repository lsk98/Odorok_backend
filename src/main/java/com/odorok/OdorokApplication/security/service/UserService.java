package com.odorok.OdorokApplication.security.service;

import com.odorok.OdorokApplication.security.domain.User;

public interface UserService {
    int updateRefreshToken(String email, String refreshToken);
    User selectByEmail(String email); // 유저 정보
    int save(User user);

    com.odorok.OdorokApplication.domain.User queryByEmail(String email);
}
