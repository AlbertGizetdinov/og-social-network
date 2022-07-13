package ru.itis.og.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.og.repository.BlacklistRepository;
import ru.itis.og.security.util.AuthorizationHeaderUtil;
import ru.itis.og.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static ru.itis.og.constant.OgConstant.AUTHENTICATION_URL;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    JwtUtil jwtUtil;

    ObjectMapper objectMapper;

    AuthorizationHeaderUtil authorizationHeaderUtil;

    BlacklistRepository blacklistRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(AUTHENTICATION_URL)) {
            filterChain.doFilter(request, response);
        } else {
            if (authorizationHeaderUtil.hasAuthorizationToken(request)) {
                String jwt = authorizationHeaderUtil.getToken(request);
                if (!blacklistRepository.exists(jwt)) {
                    try {
                        Authentication authenticationToken = jwtUtil.buildAuthentication(jwt);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);
                    } catch (JWTVerificationException e) {
                        log.info(e.getMessage());
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    objectMapper.writeValue(response.getWriter(), Collections.singletonMap("Error", "Token is expired or invalid"));
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}

