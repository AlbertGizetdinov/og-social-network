package ru.itis.og.dto.response.page;

import lombok.*;
import ru.itis.og.dto.response.PostResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPageResponse {

    List<PostResponse> posts;
    int totalPages;

}
