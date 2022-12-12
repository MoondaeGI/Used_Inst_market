package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
import com.example.Used_Inst_market.model.vo.user.UserVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PostWIthPictureVO {
    private Long postNo;
    private UserVO user;
    private String title;
    private String content;
    private Integer price;
    private SoldYN soldYN;
    private List<byte[]> imageByteArrays;

    public PostWIthPictureVO(
            Post post, List<byte[]> imageByteArrays) {
        this.postNo = post.getPostNo();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.soldYN = post.getSoldYN();
        this.user = UserVO.from(post.getUser());

        this.imageByteArrays = imageByteArrays;
    }

    public static PostWIthPictureVO of(
            Post post, List<byte[]> imageByteArrays) {
        return new PostWIthPictureVO(post, imageByteArrays);
    }
}
