package ru.itis.og.dto.response;

import lombok.*;
import ru.itis.og.model.Post;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private UUID id;
    private String title;
    private String text;
    private Instant createDate;
    private Instant updateDate;
    private Post.State state;
    private UUID account_id;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .text(post.getText())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .state(post.getState())
                .account_id(post.getAccount().getId())
                .build();
    }

    public static List<PostResponse> from(List<Post> posts) {
        return posts.stream().map(PostResponse::from).collect(Collectors.toList());
    }

}
