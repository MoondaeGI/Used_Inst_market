package com.example.Used_Inst_market.web.dto.board.post;

import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
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
    private Long upperLocalNo;
    private Long lowerLocalNo;

    @Builder
    public PostUpdateRequestDTO(Long postNo, String title, String content,
                                Integer price, SoldYN soldYN, Long upperCategoryNo,
                                Long lowerCategoryNo, Long brandNo,
                                Long upperLocalNo, Long lowerLocalNo) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = soldYN;

        this.upperCategoryNo = upperCategoryNo;
        this.lowerCategoryNo = lowerCategoryNo;
        this.brandNo = brandNo;

        this.upperLocalNo = upperLocalNo;
        this.lowerLocalNo = lowerLocalNo;
    }
}
