package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
import com.example.Used_Inst_market.model.vo.user.UserVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PostVO {
    private Long postNo;
    private UserVO user;
    private String title;
    private String content;
    private Integer price;
    private SoldYN soldYN;

    public PostVO(Post post) {
        this.postNo = post.getPostNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.soldYN = post.getSoldYN();
        this.user = UserVO.from(post.getUser());
    }

    public static PostVO from(Post post) {
        return new PostVO(post);
    }
}
