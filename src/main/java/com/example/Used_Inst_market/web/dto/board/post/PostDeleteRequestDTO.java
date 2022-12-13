package com.example.Used_Inst_market.web.dto.board.post;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDeleteRequestDTO {
    @ApiParam(name = "postNo", value = "게시글 번호", example = "1", required = true)
    private Long postNo;

    @Builder
    public PostDeleteRequestDTO(Long postNo) {
        this.postNo = postNo;
    }
}
