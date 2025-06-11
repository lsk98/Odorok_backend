package com.odorok.OdorokApplication.security.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie createRefreshTokenCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        // cookie.setSecure(true); // https에서만 쿠키 전송 - samesite 설정 안 해놓으면 필요함
        // csrf 위험을 막기 위해 samesite 속성 설정은 lax/strict으로 꼭 필요하다.
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }

}
