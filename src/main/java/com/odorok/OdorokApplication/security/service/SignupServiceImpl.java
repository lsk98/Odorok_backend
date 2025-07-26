package com.odorok.OdorokApplication.security.service;


import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.security.dto.SignupRequest;
import com.odorok.OdorokApplication.security.exception.DuplicateUserEmailException;
import com.odorok.OdorokApplication.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupServiceImpl implements SignupService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void signup(SignupRequest request) {
        User user = User.builder().email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .role("ROLE_USER").build();

        if(userRepository.existsByEmail(request.getEmail())) {
            log.debug("이미 존재하는 이메일 입니다. (email : "+request.getEmail() + ")");
            throw new DuplicateUserEmailException("이미 존재하는 이메일 입니다. (email : "+request.getEmail() + ")");
        }

        userRepository.save(user);
    }
}
