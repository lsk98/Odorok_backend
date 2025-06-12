package com.odorok.OdorokApplication.security.principal;

import com.odorok.OdorokApplication.security.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2User oAuth2User;
    private final User user;

    public CustomOAuth2User(OAuth2User oAuth2User, User user) {
        this.oAuth2User = oAuth2User;
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return user.getEmail();
    }

    public User getUser() {
        return user;
    }

//    public String getEmail() {
//        Map<String, Object> account = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
//        return (String) account.get("email");
//    }
//
//    public String getNickname() {
//        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttribute("properties");
//        return (String) properties.get("nickname");
//    }
//
//    public String getProfileImage() {
//        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttribute("properties");
//        return (String) properties.get("profile_image");
//    }
//
//    public String getName() {
//        Map<String, Object> account = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
//        return (String) account.get("name");
//    }
//
//    public String getGender() {
//        Map<String, Object> account = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
//        return (String) account.get("gender");
//    }
//
//    public String getBirthday() {
//        Map<String, Object> account = (Map<String, Object>) oAuth2User.getAttribute("kakao_account");
//        return (String) account.get("birthday"); // MMDD 형식
//    }

}
