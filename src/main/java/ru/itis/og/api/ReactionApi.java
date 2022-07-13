package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.ReactionRequest;
import ru.itis.og.dto.response.ReactionResponse;
import ru.itis.og.dto.response.page.ReactionPageResponse;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.REACTION_CONTROLLER_PATH;

@RequestMapping(REACTION_CONTROLLER_PATH)
public interface ReactionApi {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ReactionPageResponse> getReactions(@RequestBody IdPageRequest idPageRequest);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ReactionResponse> addReaction(@RequestBody ReactionRequest reactionRequest);

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteReaction(@RequestBody IdPageRequest idPageRequest);
}
