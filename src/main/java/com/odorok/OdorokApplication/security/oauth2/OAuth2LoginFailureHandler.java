//package com.odorok.OdorokApplication.security.oauth2;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        log.error("OAuth2 로그인 실패: {}", exception.getMessage());
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json;charset=UTF-8");
//
//        Map<String, Object> errorResponse = Map.of("status", "FAIL", "message", exception.getMessage());
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(response.getWriter(), errorResponse);
//    }
//}
