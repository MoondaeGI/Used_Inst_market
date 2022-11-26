package com.example.Used_Inst_market.web.vo.post;

import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.SoldYN;
import com.example.Used_Inst_market.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostInfoVO {
    private Long postNo;
    private User user;
    private String title;
    private String content;
    private SoldYN soldYN;

    @Builder
    public PostInfoVO(Post post) {
        this.postNo = post.getPostNo();
        this.user = post.getUser();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.soldYN = post.getSoldYN();
    }
}
