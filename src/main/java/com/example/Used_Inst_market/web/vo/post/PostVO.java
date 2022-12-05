package com.example.Used_Inst_market.web.vo.post;

import com.example.Used_Inst_market.domain.board.post.Post;
import com.example.Used_Inst_market.domain.board.post.SoldYN;
import com.example.Used_Inst_market.web.vo.user.UserFromSelectPostVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PostVO {
    private Long postNo;
    private UserFromSelectPostVO user;
    private String title;
    private String content;
    private Integer price;
    private SoldYN soldYN;
    private List<PictureVO> pictures;

    public PostVO(Post post, List<PictureVO> pictures) {
        this.postNo = post.getPostNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.soldYN = post.getSoldYN();

        this.user = UserFromSelectPostVO.from(post.getUser());
        this.pictures = pictures;
    }

    public PostVO(Post post) {
        this.postNo = post.getPostNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.soldYN = post.getSoldYN();

        this.user = UserFromSelectPostVO.from(post.getUser());
    }

    public static PostVO of(Post post, List<PictureVO> pictures) {
        return new PostVO(post, pictures);
    }
}
