//package com.odorok.OdorokApplication.security.jwt.filter;
//
//import com.odorok.OdorokApplication.security.jwt.JWTUtil;
//import com.odorok.OdorokApplication.security.service.CustomUserDetailsService;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class JWTVerificationFilter extends OncePerRequestFilter {
//
//    private final JWTUtil jwtUtil;
//    private final CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.debug("JWTVerificationFilter.doFilterInternal() called");
//        String token = extractToken(request);
//        if(token == null) {
//            // 토큰이 없으면 다음 필터 (JWTAuthenticationFilter로 넘어감
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        Claims claims = jwtUtil.getClaims(token);
//        // 실제 사용자 정보 조회
//        UserDetails details = userDetailsService.loadUserByUsername(claims.get("email").toString());
//
//        // Authentication 생성 및 SecurityContextHolder에 저장
//        var authentication = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // 다음 filter 호출
//        filterChain.doFilter(request, response);
//    }
//
//    private String extractToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }
//
//}
