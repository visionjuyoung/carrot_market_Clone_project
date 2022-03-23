package com.hmsh.carrotmarket.security.config;

import com.hmsh.carrotmarket.security.handler.LoginFailureHandler;
import com.hmsh.carrotmarket.security.filter.JwtAuthFilter;
import com.hmsh.carrotmarket.security.filter.LoginFilter;
import com.hmsh.carrotmarket.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    JwtAuthFilter jwtAuthenticationFilter() {
        return new JwtAuthFilter("/member/**/*", jwtUtil());
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter("/api/login", jwtUtil());
        loginFilter.setAuthenticationManager(authenticationManager());
        loginFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        return loginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
