package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.LinkRequest;
import ru.itis.og.dto.response.LinkResponse;
import ru.itis.og.dto.response.page.LinkPageResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.LinkNotFoundException;
import ru.itis.og.exception.OgServiceException;
import ru.itis.og.model.Account;
import ru.itis.og.model.Link;
import ru.itis.og.model.enumeration.State;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.LinkRepository;
import ru.itis.og.service.LinkService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static ru.itis.og.constant.OgConstant.DEFAULT_STATE;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final AccountRepository accountRepository;

    @Override
    public LinkResponse createLink(LinkRequest linkRequest) {
        Account account = accountRepository.findById(UUID.fromString(linkRequest.getAccountId()))
                .orElseThrow(AccountNotFoundException::new);
        String title = (linkRequest.getTitle() == null || linkRequest.getTitle().trim().length() == 0)
                ? linkRequest.getLink()
                : linkRequest.getTitle().trim();
        URI uri;
        try {
            uri = new URI(linkRequest.getLink());
        } catch (URISyntaxException e) {
            throw new OgServiceException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return LinkResponse.from(linkRepository.save(
                Link.builder()
                        .account(account)
                        .title(title)
                        .link(uri)
                        .state(DEFAULT_STATE)
                        .build()
        ));
    }

    @Override
    public LinkPageResponse getLinks(IdPageRequest linkPageRequest) {
        PageRequest pageRequest = PageRequest.of(
                linkPageRequest.getPage(), linkPageRequest.getSize(), Sort.by("createDate")
        );
        Page<Link> links = linkRepository.findAllByAccount_IdAndState(
                UUID.fromString(linkPageRequest.getId()), State.PUBLISHED, pageRequest
        );
        return LinkPageResponse.builder()
                .links(LinkResponse.from(links.getContent()))
                .totalPages(links.getTotalPages())
                .build();
    }

    @Override
    public void deleteLink(IdPageRequest linkPageRequest) {
        Link link = linkRepository.findById(UUID.fromString(linkPageRequest.getId()))
                .orElseThrow(LinkNotFoundException::new);
        link.setState(State.DELETED);
        linkRepository.save(link);
    }
}
