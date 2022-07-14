package ru.itis.og.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.og.model.Reaction.ReactionType;
import ru.itis.og.validation.annotation.Uuid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReactionRequest {

    @Uuid
    private String accountId;

    @Uuid
    private String postId;

    private ReactionType reactionType;

}
