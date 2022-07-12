package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.FileInformation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInformationResponse {

    private UUID id;
    private Long size;
    private String type;
    private String originalFilename;
    private String storageFilename;
    private UUID accountId;

    public static FileInformationResponse from(FileInformation fileInformation) {
        return FileInformationResponse.builder()
                .id(fileInformation.getId())
                .size(fileInformation.getSize())
                .type(fileInformation.getType())
                .originalFilename(fileInformation.getOriginalFilename())
                .storageFilename(fileInformation.getStorageFilename())
                .accountId(fileInformation.getAccount().getId())
                .build();
    }

    public static List<FileInformationResponse> from(List<FileInformation> fileInformations) {
        return fileInformations.stream().map(FileInformationResponse::from).collect(Collectors.toList());
    }
}
