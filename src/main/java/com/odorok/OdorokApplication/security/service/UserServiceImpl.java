package com.odorok.OdorokApplication.security.service;

import com.odorok.OdorokApplication.course.repository.UserRepository;
import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.security.domain.UserStuff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserStuff selectByEmail(String email) {
        // 유저 정보 조회
        UserStuff userStuff = new UserStuff(userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("이메일이 존재하지 않아용 ~~ " + email)
        ));
        log.debug("user: {}", userStuff);
        return userStuff;
    }

    @Override
    public int save(UserStuff userStuff) {
        String rawPassword = userStuff.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        userStuff.setPassword(encodedPassword);
        User user = new User(userStuff);
        userRepository.save(user);
        return 1;
    }

    @Override
    public User queryByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("없는 이메일 입니다. ("+email+")"));
    }
}
