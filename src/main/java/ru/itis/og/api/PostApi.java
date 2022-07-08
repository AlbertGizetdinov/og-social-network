package ru.itis.og.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.og.dto.request.page.PostPageRequest;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.page.PostPageResponse;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.itis.og.constant.OgConstant.POSTS_CONTROLLER_PATH;

@RequestMapping(POSTS_CONTROLLER_PATH)
public interface PostApi {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PostPageResponse> getPosts(@RequestBody PostPageRequest postPageRequest);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PostResponse> addPost(@RequestBody PostRequest postRequest);

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest);

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deletePost(@RequestBody PostRequest postRequest);
}
