package com.odorok.OdorokApplication.security.controller;

import com.odorok.OdorokApplication.security.domain.User;
import com.odorok.OdorokApplication.security.jwt.JWTUtil;
import com.odorok.OdorokApplication.security.service.UserService;
import com.odorok.OdorokApplication.security.util.CookieUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class JwtController {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.refresh.expmin}")
    private int refreshExpMin;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return handleFail(new IllegalArgumentException("Email and password are required"), HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());

//        User user = new User();
//        user.setEmail(user.getEmail());
//        user.setPassword(encodedPassword);
//        user.setNickname(user.getNickname());

        userService.save(user);

        return handleSuccess(Map.of("message", "User registered successfully"), HttpStatus.CREATED);
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request,  HttpServletResponse response) {
        String refreshToken = null;

        if(request.getCookies() != null) {
            for(Cookie cookie: request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Refresh token is required"));
        }

        Map<String, Object> claims = jwtUtil.getClaims(refreshToken);
        String email = (String) claims.get("email");

        if (email == null) {
            throw new JwtException("Invalid refresh token: email claim missing");
        }

        User user = null;
//        try {
            user = userService.selectByEmail(email);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        if (user == null || user.getRefresh() == null || !user.getRefresh().equals(refreshToken)) {
            log.warn("Invalid or mismatched refresh token for user: {}", email);
            return handleFail(new JwtException("Invalid refresh token"), HttpStatus.UNAUTHORIZED);
        }

        String newAccessToken = jwtUtil.createAccessToken(user);
        String newRefreshToken = jwtUtil.createRefreshToken(user);
//        try {
            userService.updateRefreshToken(user.getEmail(), newRefreshToken);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Cookie refreshCookie = CookieUtil.createRefreshTokenCookie("refreshToken", newRefreshToken, 1000 * 60 * refreshExpMin);
        response.addCookie(refreshCookie);

        return handleSuccess(Map.of("accessToken", newAccessToken), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Refresh token is required"));
        }
        Map<String, Object> claims = jwtUtil.getClaims(refreshToken);
        String email = (String) claims.get("email");

        if (email == null) {
            throw new JwtException("Invalid refresh token: email claim missing");
        }

        userService.updateRefreshToken(email, null);
        Cookie expiredCookie = CookieUtil.createRefreshTokenCookie("refreshToken", "", 0);
        response.addCookie(expiredCookie);
        return handleSuccess(Map.of("accessToken", ""), HttpStatus.OK);
    }

    ResponseEntity<?> handleSuccess(Object data, HttpStatus status) {
        Map<String, Object> map = Map.of("status", "SUCCESS", "data", data);
        return ResponseEntity.status(status).body(map);
    }

    ResponseEntity<?> handleFail(Exception e, HttpStatus status) {
        Map<String, Object> map = Map.of("status", "FAIL", "error", e.getMessage());
        return ResponseEntity.status(status).body(map);
    }
}
