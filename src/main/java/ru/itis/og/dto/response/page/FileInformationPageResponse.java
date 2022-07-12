package ru.itis.og.dto.response.page;

import lombok.*;
import ru.itis.og.dto.response.FileInformationResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInformationPageResponse {

    List<FileInformationResponse> files;
    int totalPage;
}
