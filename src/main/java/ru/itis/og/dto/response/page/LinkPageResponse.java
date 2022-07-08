package ru.itis.og.dto.response.page;

import lombok.*;
import ru.itis.og.dto.response.LinkResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkPageResponse {

    List<LinkResponse> links;
    int totalPages;
}
