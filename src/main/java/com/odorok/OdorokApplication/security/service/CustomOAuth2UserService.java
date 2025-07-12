//package com.odorok.OdorokApplication.security.service;
//
//import com.odorok.OdorokApplication.security.domain.User;
//import com.odorok.OdorokApplication.security.principal.CustomOAuth2User;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final UserService userService;
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest request) {
//        OAuth2User oAuth2User = super.loadUser(request);
//        Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
//
//        String email = (String) kakaoAccount.get("email");
//        // 최초 로그인 시 자동 회원가입
//        User user = userService.selectByEmail(email);
//        if (user == null) {
//            user = new User();
//            user.setEmail(email);
//            user.setNickname((String) ((Map<String, Object>) kakaoAccount.get("profile")).get("nickname"));
//            user.setName((String) kakaoAccount.get("name"));
//            user.setPassword(UUID.randomUUID().toString()); // password -> 더미 값으로 처리
//            int saveUser = userService.save(user);
//            log.debug("kakao social user saved by: {}", email);
//            log.debug("kakao social user saved success? : {} ", saveUser == 1 ? "success" : "fail");
//        }
//
//        return new CustomOAuth2User(oAuth2User, user);
//    }
//}
