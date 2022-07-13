package ru.itis.og.dto.response.page;

import lombok.*;
import ru.itis.og.dto.response.ReactionResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionPageResponse {

    List<ReactionResponse> reactions;
    int totalPages;
}
