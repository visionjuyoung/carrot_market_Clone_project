package com.hmsh.carrotmarket.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AntPathMatcher antPathMatcher;
    private final String pattern;
//    private final JwtUtil jwtUtil;

    @Value("${jwt.secret-key}")
    private String secretKey;
//
    public JwtAuthFilter(String pattern) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
//        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (antPathMatcher.match(pattern, request.getRequestURI())) { // 패턴에 매치되면 필터링
            log.info("match {}", request.getRequestURI());

            String authHeader = request.getHeader("Authorization");

            if (StringUtils.hasText(authHeader) && authHeader.equals(secretKey)) {
                filterChain.doFilter(request, response);
            }

            return;
        }

        filterChain.doFilter(request, response);

    }
}
