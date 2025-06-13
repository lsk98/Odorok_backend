package com.odorok.OdorokApplication.security.service;

import com.odorok.OdorokApplication.security.dao.UserDao;
import com.odorok.OdorokApplication.security.domain.User;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    // 사용자 인증 시 사용자 정보 가져오기
    private final UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        // TODO: userDao에서 사용자 정보 조회
//        try {
            user = userDao.selectByEmail(username);
            log.debug("CustomUserDetail user: {}", user);
            if(user == null) {
                throw new UsernameNotFoundException(username);
            } else {
                return new CustomUserDetails(user);
            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}


