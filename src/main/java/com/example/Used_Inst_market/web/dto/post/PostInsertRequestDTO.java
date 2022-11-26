package com.example.Used_Inst_market.web.dto.post;

import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostInsertRequestDTO {
    private User user;
    private String title;
    private String content;

    @Builder
    public PostInsertRequestDTO(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
