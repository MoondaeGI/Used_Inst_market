package com.example.Used_Inst_market.web.dto.board.select.categoryselect;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSelectFromUpCategoryDTO {
    @ApiParam(name = "상위 카테고리 번호", required = true,
            value = "upperCategoryNo", example = "1")
    private Long upperCategoryNo;

    @Builder
    public PostSelectFromUpCategoryDTO(Long upperCategoryNo) {

        this.upperCategoryNo = upperCategoryNo;
    }
}
