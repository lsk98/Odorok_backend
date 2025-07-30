package com.odorok.OdorokApplication.security.service;


import com.odorok.OdorokApplication.domain.User;
import com.odorok.OdorokApplication.security.dto.CustomUserDetails;
import com.odorok.OdorokApplication.security.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다."));
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
