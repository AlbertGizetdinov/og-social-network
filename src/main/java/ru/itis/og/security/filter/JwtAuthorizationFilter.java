package ru.itis.og.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.og.exception.OgUnauthorizedException;
import ru.itis.og.repository.BlacklistRepository;
import ru.itis.og.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.og.constant.OgConstant.AUTHENTICATION_URL;
import static ru.itis.og.util.HttpRequestUtil.getTokenFromRequest;
import static ru.itis.og.util.HttpRequestUtil.hasTokenInRequest;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    JwtUtil jwtUtil;
    BlacklistRepository blacklistRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equals(AUTHENTICATION_URL)) {
            filterChain.doFilter(request, response);
        } else {
            if (hasTokenInRequest(request)) {
                String jwt = getTokenFromRequest(request);
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
                    throw new OgUnauthorizedException("Token is expired or invalid");
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}

