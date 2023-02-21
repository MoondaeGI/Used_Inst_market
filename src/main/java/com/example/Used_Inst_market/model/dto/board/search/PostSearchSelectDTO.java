package com.example.Used_Inst_market.model.dto.board.search;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostSearchSelectDTO {
    @ApiParam(name = "상위 카테고리 번호", value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;
    @ApiParam(name = "하위 카테고리 번호", value = "lowerCategoryNo", example = "1")
    private Long lowerCategoryNo;
    @ApiParam(name = "브랜드 번호", value = "brandNo", example = "1")
    private Long brandNo;
    @ApiParam(name = "상위 지역 번호", value = "upperLocalNo", example = "1")
    private Long upperLocalNo;
    @ApiParam(name = "하위 지역 번호", value = "lowerLocalNo", example = "1")
    private Long lowerLocalNo;
    @ApiParam(name = "검색어", value = "keyword", example = "example")
    private String keyword;
    @ApiParam(name = "최소 가격", value = "minPrice", example = "1")
    private Integer minPrice;
    @ApiParam(name = "최대 가격", value = "maxPrice", example = "1")
    private Integer maxPrice;
    @ApiParam(name = "검색어 타입", value = "keywordType", example = "title")
    private String keywordType;
}
