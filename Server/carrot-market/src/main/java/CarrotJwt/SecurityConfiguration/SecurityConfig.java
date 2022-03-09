package CarrotJwt.SecurityConfiguration;

import CarrotJwt.Handler.AuthenticationEntryPointerHandler;
import CarrotJwt.Handler.JwtAuthenticationFilter;
import CarrotJwt.Handler.WebAccessDeniedHandler;
import CarrotJwt.JwtProvider.JwtProvider;
import JwtService.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final WebAccessDeniedHandler webAccessDeniedHandler;
    private final AuthenticationEntryPointerHandler authenticationEntryPointHandler;
    private final CustomUserDetailService customUserDetailService;

    // AuthenticationManagerBuilder의 PasswordEncoder()를 통해
    // 패스워드 암호화에 사용될 PasswordEncoder 구현체를 지정할 수 있습니다.
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**/**").hasRole("ADMIN")
                .antMatchers("/app/user/**", "/app/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler)
                .accessDeniedHandler(webAccessDeniedHandler)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), (Class<? extends Filter>) UsernamePasswordAuthenticationFilter.class);
    }
}
