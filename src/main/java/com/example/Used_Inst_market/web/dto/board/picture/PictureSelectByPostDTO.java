package com.example.Used_Inst_market.web.dto.board.picture;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureSelectByPostDTO {
    @ApiParam(name = "게시글 번호", readOnly = true,
            value = "postNo", example = "1")
    private Long postNo;

    @Builder
    public PictureSelectByPostDTO(Long postNo) {
        this.postNo = postNo;
    }
}
