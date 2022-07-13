package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Reaction;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionResponse {

    private UUID id;
    private UUID accountId;
    private UUID postId;
    private Instant createDate;
    private Reaction.ReactionType reaction;

    public static ReactionResponse from(Reaction reaction) {
        return ReactionResponse.builder()
                .id(reaction.getId())
                .accountId(reaction.getAccount().getId())
                .postId(reaction.getPost().getId())
                .createDate(reaction.getCreateDate())
                .reaction(reaction.getReaction())
                .build();
    }

    public static List<ReactionResponse> from(List<Reaction> reactions) {
        return reactions.stream().map(ReactionResponse::from).collect(Collectors.toList());
    }
}
