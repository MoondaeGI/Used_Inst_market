package com.example.Used_Inst_market.model.dto.category.select;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategorySelectFromPostDTO {
    @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
    private Long postNo;

    @Builder
    public CategorySelectFromPostDTO(Long postNo) {
        this.postNo = postNo;
    }
}
