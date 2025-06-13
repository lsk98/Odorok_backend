package com.odorok.OdorokApplication.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odorok.OdorokApplication.security.domain.User;
import com.odorok.OdorokApplication.security.jwt.JWTUtil;
import com.odorok.OdorokApplication.security.principal.CustomOAuth2User;
import com.odorok.OdorokApplication.security.service.UserService;
import com.odorok.OdorokApplication.security.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;
    private final UserService userService;

    @Value("${jwt.refresh.expmin}")
    private int refreshExpMin;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User customUser = (CustomOAuth2User) authentication.getPrincipal();
        User user = customUser.getUser();
        log.debug("login user: {}", user);
        String accessToken = jwtUtil.createAccessToken(user);
        String refreshToken = jwtUtil.createRefreshToken(user);

        int n = userService.updateRefreshToken(user.getEmail(), refreshToken);
        log.debug("update RefreshToken success: {}", n == 1 ? "success" : "fail");

        Cookie refreshCookie = CookieUtil.createRefreshTokenCookie("refreshToken", refreshToken, 1000 * 60 * refreshExpMin);
        response.addCookie(refreshCookie);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        new ObjectMapper().writeValue(response.getWriter(),
                Map.of("status", "SUCCESS", "accessToken", accessToken));
    }
}
