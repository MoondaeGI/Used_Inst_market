package com.example.Used_Inst_market.web.dto.board.picture;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureDeleteDTO {
    @ApiParam(name = "게시글 번호", required = true,
            value = "postNo", example = "1")
    private Long postNo;

    @Builder
    public PictureDeleteDTO(Long postNo) {
        this.postNo = postNo;
    }
}
