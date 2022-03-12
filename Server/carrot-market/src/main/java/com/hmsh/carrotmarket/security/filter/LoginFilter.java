package com.hmsh.carrotmarket.security.filter;

import com.hmsh.carrotmarket.dto.AuthMemberDTO;
import com.hmsh.carrotmarket.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtUtil jwtUtil;

    public LoginFilter(String defaultFilterProcessesUrl, JwtUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(phoneNumber, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication!!");
        String phoneNumber = ((AuthMemberDTO) authResult.getPrincipal()).getPhoneNumber();
        String token;

        try {
            token = jwtUtil.generateToken(phoneNumber);

            response.setContentType("application/json;charset=utf-8");
            JSONObject json = new JSONObject();
            json.put("token", token);

            PrintWriter out = response.getWriter();
            out.print(json);

            log.info(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
