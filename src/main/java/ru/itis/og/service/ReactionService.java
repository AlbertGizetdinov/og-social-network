package ru.itis.og.service;

import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.ReactionRequest;
import ru.itis.og.dto.response.ReactionResponse;
import ru.itis.og.dto.response.page.ReactionPageResponse;

public interface ReactionService {

    ReactionPageResponse getReactions(IdPageRequest idPageRequest);

    ReactionResponse createReaction(ReactionRequest reactionRequest);

    void deleteReaction(IdPageRequest idPageRequest);
}
