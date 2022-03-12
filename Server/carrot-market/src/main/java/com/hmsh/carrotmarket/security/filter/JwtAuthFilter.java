package com.hmsh.carrotmarket.security.filter;

import com.hmsh.carrotmarket.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AntPathMatcher antPathMatcher;
    private final String pattern;
    private final JwtUtil jwtUtil;


    public JwtAuthFilter(String pattern, JwtUtil jwtUtil) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (antPathMatcher.match(pattern, request.getRequestURI())) { // 패턴에 매치되면 필터링
            log.info("match {}", request.getRequestURI());

            String authHeader = request.getHeader("Authorization");

            if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {

                String subject = jwtUtil.getSubject(authHeader.substring(7));
                if (subject.length() > 0) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json;charset=utf-8");
                    JSONObject json = new JSONObject();
                    String message = "유효하지 않은 토큰";
                    json.put("code", "403");
                    json.put("message", message);

                    PrintWriter out = response.getWriter();
                    out.print(json);
                }

            }

            return;
        }

        filterChain.doFilter(request, response);

    }
}
