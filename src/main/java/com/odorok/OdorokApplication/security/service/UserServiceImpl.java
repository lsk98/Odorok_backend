package com.odorok.OdorokApplication.security.service;

import com.odorok.OdorokApplication.security.dao.UserDao;
import com.odorok.OdorokApplication.security.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int updateRefreshToken(String email, String refreshToken) {
        // 리프레시 토큰 업데이트
        return userDao.updateRefreshToken(email, refreshToken);
    }

    @Override
    public User selectByEmail(String email) {
        // 유저 정보 조회
        User user = userDao.selectByEmail(email);
        log.debug("user: {}", user);
        return user;
    }

    @Override
    public int save(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        return userDao.save(user);
    }
}
