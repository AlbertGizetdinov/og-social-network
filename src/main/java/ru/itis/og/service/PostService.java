package ru.itis.og.service;

import ru.itis.og.dto.request.PostPageRequest;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.page.PostPageResponse;

public interface PostService {

    PostPageResponse getPosts(PostPageRequest postPageRequest);

    PostResponse createPost(PostRequest postRequest);

    PostResponse updatePost(PostRequest postRequest);

    void deletePost(PostRequest postRequest);

}
