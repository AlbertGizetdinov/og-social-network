package ru.itis.og.service;

import ru.itis.og.dto.request.DescriptionRequest;
import ru.itis.og.dto.response.DescriptionResponse;

import java.util.UUID;

public interface DescriptionService {

    DescriptionResponse addDescription(DescriptionRequest descriptionRequest, UUID accountId);

    DescriptionResponse updateDescription(DescriptionRequest descriptionRequest, UUID accountId);

    DescriptionResponse getDescription(UUID accountId);

}
