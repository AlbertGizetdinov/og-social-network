package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.ReactionApi;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.ReactionRequest;
import ru.itis.og.dto.response.ReactionResponse;
import ru.itis.og.dto.response.page.ReactionPageResponse;
import ru.itis.og.service.ReactionService;

@RestController
@RequiredArgsConstructor
public class ReactionController implements ReactionApi {

    private final ReactionService reactionService;

    @Override
    public ResponseEntity<ReactionPageResponse> getReactions(IdPageRequest idPageRequest) {
        return ResponseEntity.ok(reactionService.getReactions(idPageRequest));
    }

    @Override
    public ResponseEntity<ReactionResponse> addReaction(ReactionRequest reactionRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(reactionService.createReaction(reactionRequest));
    }

    @Override
    public ResponseEntity<?> deleteReaction(IdPageRequest idPageRequest) {
        reactionService.deleteReaction(idPageRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
