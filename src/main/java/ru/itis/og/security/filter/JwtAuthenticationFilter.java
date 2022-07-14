package ru.itis.og.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.itis.og.exception.OgUnauthorizedException;
import ru.itis.og.model.Account;
import ru.itis.og.security.authentication.RefreshTokenAuthentication;
import ru.itis.og.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.og.constant.OgConstant.AUTHENTICATION_URL;
import static ru.itis.og.constant.OgConstant.USERNAME_PARAMETER;
import static ru.itis.og.util.HttpRequestUtil.getTokenFromRequest;
import static ru.itis.og.util.HttpRequestUtil.hasTokenInRequest;
import static ru.itis.og.util.HttpResponseUtil.putTokensInResponse;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    ObjectMapper objectMapper;
    JwtUtil jwtUtil;

    public JwtAuthenticationFilter(ObjectMapper objectMapper,
                                   JwtUtil jwtUtil,
                                   AuthenticationConfiguration authenticationConfiguration) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USERNAME_PARAMETER);
        this.setFilterProcessesUrl(AUTHENTICATION_URL);
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (hasTokenInRequest(request)) {
            String refreshToken = getTokenFromRequest(request);
            RefreshTokenAuthentication refreshTokenAuthentication = new RefreshTokenAuthentication(refreshToken);
            return super.getAuthenticationManager().authenticate(refreshTokenAuthentication);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        putTokensInResponse(request, response, (Account) authResult.getPrincipal(), jwtUtil, objectMapper);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        throw new OgUnauthorizedException(failed.getMessage());
    }
}
