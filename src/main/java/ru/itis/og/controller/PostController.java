package ru.itis.og.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.og.api.PostApi;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.PostsPage;
import ru.itis.og.service.PostService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

    private final PostService postService;

    @Override
    public ResponseEntity<PostsPage> getPosts(int page) {
        return null;
    }

    @Override
    public ResponseEntity<PostResponse> addPost(PostRequest postRequest) {
        return null;
    }

    @Override
    public ResponseEntity<PostResponse> updatePost(UUID id, PostRequest postRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePost(UUID id) {
        return null;
    }
}
