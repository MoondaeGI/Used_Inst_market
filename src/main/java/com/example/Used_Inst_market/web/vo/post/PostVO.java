package com.example.Used_Inst_market.web.vo.post;

import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.SoldYN;
import com.example.Used_Inst_market.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostVO {
    private Long postNo;
    private User user;
    private String title;
    private String content;
    private Integer price;
    private SoldYN soldYN;

    @Builder
    public PostVO(Post post) {
        this.postNo = post.getPostNo();
        this.user = post.getUser();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.soldYN = post.getSoldYN();
    }
}
