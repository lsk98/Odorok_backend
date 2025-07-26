package com.odorok.OdorokApplication.security.service;

import com.odorok.OdorokApplication.security.domain.UserStuff;

public interface UserService {
//    int updateRefreshToken(String email, String refreshToken);
    UserStuff selectByEmail(String email); // 유저 정보
    int save(UserStuff userStuff);

    com.odorok.OdorokApplication.domain.User queryByEmail(String email);
}
