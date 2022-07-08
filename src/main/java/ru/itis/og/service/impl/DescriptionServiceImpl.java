package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.DescriptionRequest;
import ru.itis.og.dto.response.DescriptionResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.DescriptionNotFoundException;
import ru.itis.og.model.Account;
import ru.itis.og.model.Description;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.DescriptionRepository;
import ru.itis.og.service.DescriptionService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final DescriptionRepository descriptionRepository;

    private final AccountRepository accountRepository;

    @Override
    public DescriptionResponse addDescription(DescriptionRequest descriptionRequest, UUID accountId) {
        Account existingAccount = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        Description description = Description.builder()
                .status(descriptionRequest.getStatus())
                .hometown(descriptionRequest.getHometown())
                .country(descriptionRequest.getCountry())
                .phoneNumber(descriptionRequest.getPhoneNumber())
                .personInfo(descriptionRequest.getPersonInfo())
                .personalWebsite(descriptionRequest.getPersonalWebsite())
                .job(descriptionRequest.getJob())
                .account(existingAccount)
                .build();

        return DescriptionResponse.from(descriptionRepository.save(description));
    }

    @Override
    public DescriptionResponse updateDescription(DescriptionRequest descriptionRequest, UUID accountId) {
        Description description = descriptionRepository.findDescriptionByAccount_Id(accountId);
        if (description != null) {

            description.setStatus(descriptionRequest.getStatus());
            description.setHometown(descriptionRequest.getHometown());
            description.setCountry(descriptionRequest.getCountry());
            description.setPhoneNumber(descriptionRequest.getPhoneNumber());
            description.setPersonInfo(descriptionRequest.getPersonInfo());
            description.setPersonalWebsite(descriptionRequest.getPersonalWebsite());
            description.setJob(descriptionRequest.getJob());

            return DescriptionResponse.from(descriptionRepository.save(description));
        } else {
            throw new DescriptionNotFoundException();
        }

    }

    @Override
    public DescriptionResponse getDescription(UUID accountId) {
        Description description = descriptionRepository.findDescriptionByAccount_Id(accountId);
        if (description != null) {
            return DescriptionResponse.from(description);
        } else {
            throw new DescriptionNotFoundException();
        }

    }
}
