package com.example.Used_Inst_market.model.dto.board.post;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateSoldYNDTO {
    @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
    private Long postNo;

    @Builder
    public PostUpdateSoldYNDTO(Long postNo) {
        this.postNo = postNo;
    }
}
