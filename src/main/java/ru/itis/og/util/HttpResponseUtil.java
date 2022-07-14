package ru.itis.og.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import ru.itis.og.exception.FileInformationNotFoundException;
import ru.itis.og.model.Account;
import ru.itis.og.model.FileInformation;
import ru.itis.og.security.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@UtilityClass
public class HttpResponseUtil {

    public static void putFileInResponse(String storagePath,
                                         FileInformation fileInformation,
                                         HttpServletResponse response) {

        response.setContentType(fileInformation.getType());
        response.setContentLength(fileInformation.getSize().intValue());
        response.setHeader("Content-Disposition", "filename=\"" + fileInformation.getOriginalFilename() + "\"");
        try {
            IOUtils.copy(
                    Files.newInputStream(
                            Paths.get(
                                    storagePath + "\\" + fileInformation.getStorageFilename()
                            )
                    ), response.getOutputStream()
            );
        } catch (IOException e) {
            throw new FileInformationNotFoundException();
        }
    }

    public static void putTokensInResponse(HttpServletRequest request, HttpServletResponse response, Account account,
                                           JwtUtil jwtUtil, ObjectMapper objectMapper) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> tokenJson = jwtUtil.generateTokens(
                account.getUsername(),
                account.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURL().toString());

        objectMapper.writeValue(response.getOutputStream(), tokenJson);
    }
}
