package ru.itis.og.service;

import ru.itis.og.dto.request.IdPageRequest;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.page.PostPageResponse;

public interface PostService {

    PostPageResponse getPosts(IdPageRequest idPageRequest);

    PostResponse createPost(PostRequest postRequest);

    PostResponse updatePost(PostRequest postRequest);

    void deletePost(PostRequest postRequest);

}
