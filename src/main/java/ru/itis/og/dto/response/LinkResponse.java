package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Link;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkResponse {

    private UUID id;
    private UUID accountId;
    private String title;
    private String link;
    private Instant createDate;

    public static LinkResponse from(Link link) {
        return LinkResponse.builder()
                .id(link.getId())
                .accountId(link.getAccount().getId())
                .title(link.getTitle())
                .link(link.getLink().toString())
                .createDate(link.getCreateDate())
                .build();
    }

    public static List<LinkResponse> from(List<Link> links) {
        return links.stream().map(LinkResponse::from).collect(Collectors.toList());
    }
}
