package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionRequest {

    @Size(max = 255)
    private String status;

    @Size(max = 30)
    private String hometown;

    @Size(max = 30)
    private String country;

    @Pattern(message = "phone number is incorrect", regexp = "\\+79[0-9]{9}|89[0-9]{9}")
    private String phoneNumber;

    @Size(max = 1000)
    private String personInfo;

    @Size(max = 100)
    private String personalWebsite;

    @Size(max = 50)
    private String job;

}
