package ru.itis.og.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.og.dto.request.page.PostPageRequest;
import ru.itis.og.dto.request.PostRequest;
import ru.itis.og.dto.response.PostResponse;
import ru.itis.og.dto.response.page.PostPageResponse;
import ru.itis.og.exception.AccountNotFoundException;
import ru.itis.og.exception.OgServiceException;
import ru.itis.og.exception.PostNotFountException;
import ru.itis.og.model.Post;
import ru.itis.og.model.enumeration.State;
import ru.itis.og.repository.AccountRepository;
import ru.itis.og.repository.PostRepository;
import ru.itis.og.service.PostService;

import java.time.Instant;
import java.util.UUID;

import static ru.itis.og.constant.OgConstant.DEFAULT_STATE;
import static ru.itis.og.constant.OgConstant.TIME_TO_EDIT_POST;
import static ru.itis.og.dto.response.PostResponse.from;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final AccountRepository accountRepository;

    @Override
    public PostPageResponse getPosts(PostPageRequest postPageRequest) {
        PageRequest pageRequest = PageRequest.of(postPageRequest.getPage(), postPageRequest.getSize(), Sort.by("createDate"));
        Page<Post> postPage = postRepository.findAllByAccount_IdAndState(UUID.fromString(postPageRequest.getAccountId()),
                DEFAULT_STATE, pageRequest);
        return PostPageResponse.builder()
                .posts(from(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .build();
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post post = Post.builder()
                .createDate(Instant.now())
                .title(postRequest.getTitle())
                .text(postRequest.getText())
                .updateDate(null)
                .state(State.PUBLISHED)
                .account(accountRepository.findById(UUID.fromString(postRequest.getAccountId()))
                        .orElseThrow(AccountNotFoundException::new))
                .build();
        return from(postRepository.save(post));
    }

    @Override
    public PostResponse updatePost(PostRequest postRequest) {
        Post post = postRepository.findById(UUID.fromString(postRequest.getId())).orElseThrow(PostNotFountException::new);
        if (Instant.now().isBefore(post.getCreateDate().plusSeconds(TIME_TO_EDIT_POST))) {
            if (post.getState() != null) {
                post.setTitle(postRequest.getTitle());
            }
            if (post.getText() != null) {
                post.setText(postRequest.getText());
            }
            post.setUpdateDate(Instant.now());
            return from(postRepository.save(post));
        }
        throw new OgServiceException(HttpStatus.BAD_REQUEST, "You can no longer edit this post");
    }

    @Override
    public void deletePost(PostRequest postRequest) {
        Post post = postRepository.findById(UUID.fromString(postRequest.getId())).orElseThrow(PostNotFountException::new);
        post.setState(State.DELETED);
        postRepository.save(post);
    }
}
