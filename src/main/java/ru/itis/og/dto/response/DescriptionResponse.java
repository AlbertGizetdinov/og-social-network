package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Description;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionResponse {

    private UUID id;
    private String status;
    private String hometown;
    private String country;
    private String phoneNumber;
    private String personInfo;
    private String personalWebsite;
    private String job;
    private UUID accountId;

    public static DescriptionResponse from(Description description) {
        return DescriptionResponse.builder()
                .id(description.getId())
                .status(description.getStatus())
                .hometown(description.getHometown())
                .country(description.getCountry())
                .phoneNumber(description.getPhoneNumber())
                .personInfo(description.getPersonInfo())
                .personalWebsite(description.getPersonalWebsite())
                .job(description.getJob())
                .accountId(description.getAccount().getId())
                .build();
    }

    public static List<DescriptionResponse> from(List<Description> descriptions) {
        return descriptions.stream().map(DescriptionResponse::from).collect(Collectors.toList());
    }

}
