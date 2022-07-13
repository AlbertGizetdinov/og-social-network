package ru.itis.og.security.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.og.security.detail.AccountUserDetailsImpl;
import ru.itis.og.security.detail.AccountUserDetailsServiceImpl;
import ru.itis.og.security.filter.JwtAuthenticationFilter;
import ru.itis.og.security.filter.JwtAuthorizationFilter;
import ru.itis.og.security.filter.JwtLogoutFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig {

    UserDetailsService userDetailsServiceImpl;

    PasswordEncoder passwordEncoder;

    AuthenticationProvider refreshTokenAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter,
                                                   JwtAuthorizationFilter jwtAuthorizationFilter,
                                                   JwtLogoutFilter jwtLogoutFilter) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilter(jwtAuthenticationFilter);
        httpSecurity.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtLogoutFilter, JwtAuthenticationFilter.class);

        httpSecurity.authorizeRequests()
                .antMatchers("/api/v1/signUp").permitAll()
                .antMatchers("/api/v1/confirm/**").permitAll()
                .antMatchers("/api/v1/subscriptions/**").authenticated()
                .antMatchers("/api/v1/posts/**").authenticated()
                .antMatchers("/api/v1/links/**").authenticated()
                .antMatchers("/api/v1/accounts/**").authenticated()
                .antMatchers("/api/v1/products/**").authenticated()
                .antMatchers("/api/v1/questions/**").authenticated()
                .antMatchers("/api/v1/files/**").authenticated()
                .antMatchers("/api/v1/auth/token").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll();

        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(refreshTokenAuthenticationProvider);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

}

