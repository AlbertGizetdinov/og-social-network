package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.PostApi;
import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.page.PostPageResponse;
import ru.itis.og.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

    private final PostService postService;

    @Override
    public ResponseEntity<PostPageResponse> getPosts(IdPageRequest postPageRequest) {
        return ResponseEntity.ok(postService.getPosts(postPageRequest));
    }

    @Override
    public ResponseEntity<PostResponse> addPost(PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(postService.createPost(postRequest));
    }

    @Override
    public ResponseEntity<PostResponse> updatePost(PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(postService.updatePost(postRequest));
    }

    @Override
    public ResponseEntity<?> deletePost(PostRequest postRequest) {
        postService.deletePost(postRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
