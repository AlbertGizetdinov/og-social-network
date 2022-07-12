package ru.itis.og.util;

import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import ru.itis.og.exception.FileInformationNotFoundException;
import ru.itis.og.model.FileInformation;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
