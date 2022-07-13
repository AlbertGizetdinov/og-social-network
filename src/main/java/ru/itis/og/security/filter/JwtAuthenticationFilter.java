package ru.itis.og.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.itis.og.security.authentication.RefreshTokenAuthentication;
import ru.itis.og.security.detail.AccountUserDetailsImpl;
import ru.itis.og.security.util.AuthorizationHeaderUtil;
import ru.itis.og.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static ru.itis.og.constant.OgConstant.AUTHENTICATION_URL;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME_PARAMETER = "email";

    private final ObjectMapper objectMapper;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    public JwtAuthenticationFilter(ObjectMapper objectMapper,
                                   JwtUtil jwtUtil,
                                   AuthorizationHeaderUtil authorizationHeaderUtil,
                                   AuthenticationConfiguration authenticationConfiguration) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USERNAME_PARAMETER);
        this.setFilterProcessesUrl(AUTHENTICATION_URL);
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.authorizationHeaderUtil = authorizationHeaderUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (hasRefreshToken(request)) {
            String refreshToken = authorizationHeaderUtil.getToken(request);
            RefreshTokenAuthentication refreshTokenAuthentication = new RefreshTokenAuthentication(refreshToken);
            return super.getAuthenticationManager().authenticate(refreshTokenAuthentication);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        writeTokens(request, response, (AccountUserDetailsImpl) authResult.getPrincipal());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private void writeTokens(HttpServletRequest request, HttpServletResponse response, AccountUserDetailsImpl accountUserDetails) throws IOException {
        response.setContentType("application/json");

        Map<String, String> tokenJson = jwtUtil.generateTokens(
                accountUserDetails.getUsername(),
                accountUserDetails.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURL().toString());

        objectMapper.writeValue(response.getOutputStream(), tokenJson);
    }

    private boolean hasRefreshToken(HttpServletRequest request) {
        return authorizationHeaderUtil.hasAuthorizationToken(request);
    }

}
