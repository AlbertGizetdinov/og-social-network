package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.PostsPage;

import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.POSTS_CONTROLLER_PATH;
import static ru.itis.og.constant.OgConstant.POST_ID_PATH;

@RequestMapping(POSTS_CONTROLLER_PATH)
public interface PostApi {

    @GetMapping(value = POST_ID_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PostsPage> getPosts(@Valid @RequestParam("page") int page);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostRequest postRequest);

    @PutMapping(value = POST_ID_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PostResponse> updatePost(@Valid @PathVariable("post-id") UUID id,
                                            @Valid @RequestBody PostRequest postRequest);

    @DeleteMapping(value = POST_ID_PATH, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deletePost(@Valid @PathVariable("post-id") UUID id);
}
