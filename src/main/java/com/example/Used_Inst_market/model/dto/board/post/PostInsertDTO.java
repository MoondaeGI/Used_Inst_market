package com.example.Used_Inst_market.model.dto.board.post;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostInsertDTO {
    @ApiParam()
    private Long userNo;
    @ApiParam(name = "게시글 제목", required = true,
            value = "title", example = "example")
    private String title;
    @ApiParam(name = "게시글 내용", required = true,
            value = "content", example = "example")
    private String content;
    @ApiParam(name = "상품 가격", required = true,
            value = "price", example = "1")
    private Integer price;
    @ApiParam(name = "상위 카테고리 번호", required = true,
            value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;
    @ApiParam(name = "하위 카테고리 번호", required = true,
            value = "lowerCategoryNo", example = "1")
    private Long lowerCategoryNo;
    @ApiParam(name = "브랜드 번호", required = true,
            value = "brandNo", example = "1")
    private Long brandNo;
    @ApiParam(name = "상위 지역 번호", required = true,
            value = "upperLocalNo", example = "1")
    private Long upperLocalNo;
    @ApiParam(name = "하위 지역 번호", required = true,
            value = "lowerLocalNo", example = "1")
    private Long lowerLocalNo;
    @Builder
    public PostInsertDTO(
            Long userNo, String title, String content, Integer price,
            Long upperCategoryNo, Long lowerCategoryNo, Long brandNo,
            Long upperLocalNo, Long lowerLocalNo) {
        this.userNo = userNo;
        this.title = title;
        this.content = content;
        this.price = price;

        this.upperCategoryNo = upperCategoryNo;
        this.lowerCategoryNo = lowerCategoryNo;
        this.brandNo = brandNo;

        this.upperLocalNo = upperLocalNo;
        this.lowerLocalNo = lowerLocalNo;
    }
}
