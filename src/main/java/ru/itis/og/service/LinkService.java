package ru.itis.og.service;

import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.LinkRequest;
import ru.itis.og.dto.response.LinkResponse;
import ru.itis.og.dto.response.page.LinkPageResponse;

public interface LinkService {
    LinkResponse createLink(LinkRequest linkRequest);

    LinkPageResponse getLinks(IdPageRequest linkPageRequest);

    void deleteLink(IdPageRequest linkPageRequest);
}
