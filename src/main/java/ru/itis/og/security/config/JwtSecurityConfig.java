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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.itis.og.security.filter.JwtAuthenticationFilter;
import ru.itis.og.security.filter.JwtAuthorizationFilter;
import ru.itis.og.security.filter.JwtLogoutFilter;

import static ru.itis.og.constant.OgConstant.*;

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
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilter(jwtAuthenticationFilter);
        httpSecurity.addFilterBefore(jwtLogoutFilter, JwtAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtAuthorizationFilter, JwtLogoutFilter.class);

        httpSecurity.authorizeRequests()
                .antMatchers(SIGN_UP_CONTROLLER_PATH).permitAll()
                .antMatchers(API_PATH + CONFIRM_PATH + "/**").permitAll()
                .antMatchers(SUBSCRIPTION_CONTROLLER_PATH + "/**").authenticated()
                .antMatchers(POST_CONTROLLER_PATH + "/**").authenticated()
                .antMatchers(LINK_CONTROLLER_PATH + "/**").authenticated()
                .antMatchers(ACCOUNTS_PATH + "/**").authenticated()
                .antMatchers(PRODUCT_CONTROLLER_PATH + "/**").authenticated()
                .antMatchers(QUESTION_CONTROLLER_PATH + "/**").authenticated()
                .antMatchers(FILE_CONTROLLER_PATH + "/**").authenticated()
                .antMatchers(AUTHENTICATION_URL).permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll();

        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(refreshTokenAuthenticationProvider);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

}

