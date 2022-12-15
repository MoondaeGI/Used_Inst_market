package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
import com.example.Used_Inst_market.model.vo.user.UserVO;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PostVO {
    @ApiParam(name = "게시글 번호", required = true,
            value = "postNo", example = "1")
    private Long postNo;
    @ApiParam(name = "유저", required = true)
    private UserVO user;
    @ApiParam(name = "게시글 제목", required = true,
            value = "title", example = "example")
    private String title;
    @ApiParam(name = "게시글 내용", required = true,
            value = "content", example = "example")
    private String content;
    @ApiParam(name = "상품 가격", required = true,
            value = "price", example = "1")
    private Integer price;
    @ApiParam(name = "판매 여부", required = true,
            value = "soldYN", example = "SALE")
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
