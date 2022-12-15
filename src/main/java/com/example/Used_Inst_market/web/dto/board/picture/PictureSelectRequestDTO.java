package com.example.Used_Inst_market.web.dto.board.picture;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureSelectRequestDTO {
    @ApiParam(name = "이미지 번호", readOnly = true,
            value = "pictureNo", example = "1")
    private Long pictureNo;

    @Builder
    public PictureSelectRequestDTO(Long pictureNo) {
        this.pictureNo = pictureNo;
    }
}
