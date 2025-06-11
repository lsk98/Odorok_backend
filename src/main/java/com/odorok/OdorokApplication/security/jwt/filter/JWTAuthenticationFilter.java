package com.odorok.OdorokApplication.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odorok.OdorokApplication.security.domain.User;
import com.odorok.OdorokApplication.security.service.UserService;
import com.odorok.OdorokApplication.security.util.CookieUtil;
import com.odorok.OdorokApplication.security.jwt.JWTUtil;
import com.odorok.OdorokApplication.security.principal.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Component
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JWTUtil jwtUtil;
    @Value("${jwt.refresh.expmin}")
    private int refreshExpMin;
    private final UserService userService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, JWTUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.setFilterProcessesUrl("/api/auth/login"); // 로그인 url 설정
    }

    // JSON으로 로그인 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> loginData = mapper.readValue(request.getInputStream(), Map.class);

            String email = loginData.get("email");
            String password = loginData.get("password");
            log.debug("Trying to load user by email: {}", email);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

            return this.getAuthenticationManager().authenticate(authRequest);

        } catch(IOException e) {
            throw new RuntimeException("로그인 JSON 파싱 실패", e);
        }
    }

    // 로그인 성공 시 JWT 발급
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                         Authentication authentication) {
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        User user = details.getUser();
        log.debug("user: {}", user);
        user.setPassword(null);

        String accessToken = jwtUtil.createAccessToken(details.getUser());
        String refreshToken = jwtUtil.createRefreshToken(details.getUser());
//        try {
            // 리프레스 토큰 회원 테이블에 업데이트
            userService.updateRefreshToken(user.getEmail(), refreshToken);
//        }
//        catch(SQLException e) {
//            throw new RuntimeException("Database error: 리프레시 토큰 업데이트 중 에러 발생", e);
//        }

        // 리프레시 토큰은 HttpOnly 쿠키로 전송
        Cookie refreshCookie = CookieUtil.createRefreshTokenCookie("refreshToken", refreshToken, 1000 * 60 * refreshExpMin);
        response.addCookie(refreshCookie);

        // accessToken만 JSON 응답으로 전송
        Map<String, String> result = Map.of("status", "SUCCESS", "accessToken", accessToken);
        handleResult(response, result, HttpStatus.OK);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        throw failed;
    }
    private void handleResult(HttpServletResponse response, Map<String, ?> data, HttpStatus status) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(data);
            response.setStatus(status.value());
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            log.error("Error writing JSON response", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
