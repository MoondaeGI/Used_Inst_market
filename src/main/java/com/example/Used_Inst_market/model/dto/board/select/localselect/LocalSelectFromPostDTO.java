package com.example.Used_Inst_market.model.dto.board.select.localselect;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LocalSelectFromPostDTO {
    @ApiParam(name = "게시글 번호", readOnly = true, value = "postNo", example = "1")
    private Long postNo;

    @Builder
    public LocalSelectFromPostDTO(Long postNo) {
        this.postNo = postNo;
    }
}
