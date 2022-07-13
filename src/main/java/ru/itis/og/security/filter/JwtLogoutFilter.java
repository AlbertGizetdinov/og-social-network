package ru.itis.og.security.filter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.og.repository.BlacklistRepository;
import ru.itis.og.security.util.AuthorizationHeaderUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtLogoutFilter extends OncePerRequestFilter {

    BlacklistRepository blacklistRepository;

    AuthorizationHeaderUtil authorizationHeaderUtil;

    static RequestMatcher logoutRequest = new AntPathRequestMatcher("/logout", "GET");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            String token = authorizationHeaderUtil.getToken(request);
            if (token != null) {
                blacklistRepository.save(token);
                SecurityContextHolder.getContext();
                return;
            }
        }

        filterChain.doFilter(request, response);

    }
}
