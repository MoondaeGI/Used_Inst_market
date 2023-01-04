package com.example.Used_Inst_market.model.dto.category.lower;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerCategoryDeleteDTO {
    @ApiParam(name = "하위 카테고리 번호", required = true,
            value = "lowerCategoryNo", example = "1")
    private Long lowerCategoryNo;

    @Builder
    public LowerCategoryDeleteDTO(Long lowerCategoryNo) {
        this.lowerCategoryNo = lowerCategoryNo;
    }
}
