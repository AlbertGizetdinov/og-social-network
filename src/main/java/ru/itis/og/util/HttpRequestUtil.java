package ru.itis.og.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

import static ru.itis.og.constant.OgConstant.AUTHORIZATION_HEADER_NAME;
import static ru.itis.og.constant.OgConstant.BEARER;

@UtilityClass
public class HttpRequestUtil {

    public static boolean hasTokenInRequest(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER_NAME).substring(BEARER.length());
    }
}
