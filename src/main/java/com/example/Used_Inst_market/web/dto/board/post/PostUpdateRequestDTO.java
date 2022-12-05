package com.example.Used_Inst_market.web.dto.board.post;

import com.example.Used_Inst_market.domain.board.post.SoldYN;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDTO {
    private Long postNo;
    private String title;
    private String content;
    private Integer price;
    private SoldYN soldYN;
    private Long upperCategoryNo;
    private Long lowerCategoryNo;
    private Long brandNo;

    @Builder
    public PostUpdateRequestDTO(Long postNo, String title, String content,
                                Integer price, SoldYN soldYN, Long upperCategoryNo,
                                Long lowerCategoryNo, Long brandNo) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = soldYN;
        this.upperCategoryNo = upperCategoryNo;
        this.lowerCategoryNo = lowerCategoryNo;
        this.brandNo = brandNo;
    }
}
