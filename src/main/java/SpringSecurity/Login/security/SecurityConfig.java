package SpringSecurity.Login.security;

import SpringSecurity.Login.security.jwt.JwTokenVerifier;
import SpringSecurity.Login.security.jwt.JwtEmailAndPasswordAuthenticationFilter;
import SpringSecurity.Login.user.role.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static SpringSecurity.Login.user.role.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtEmailAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwTokenVerifier(), JwtEmailAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/api/v1/book/write").hasRole(ADMIN.name())
                .anyRequest().permitAll();
    }
}
