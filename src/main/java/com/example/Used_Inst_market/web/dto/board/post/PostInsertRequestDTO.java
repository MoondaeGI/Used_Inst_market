package com.example.Used_Inst_market.web.dto.board.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostInsertRequestDTO {
    private Long userNo;
    private String title;
    private String content;
    private Integer price;
    private Long upperCategoryNo;
    private Long lowerCategoryNo;
    private Long brandNo;

    @Builder
    public PostInsertRequestDTO(
            Long userNo, String title, String content, Integer price,
            Long upperCategoryNo, Long lowerCategoryNo, Long brandNo) {
        this.userNo = userNo;
        this.title = title;
        this.content = content;
        this.price = price;
        this.upperCategoryNo = upperCategoryNo;
        this.lowerCategoryNo = lowerCategoryNo;
        this.brandNo = brandNo;
    }
}
