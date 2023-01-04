package com.example.Used_Inst_market.model.dto.board.select.localselect;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSelectFromUpLocalDTO {
    @ApiParam(name = "상위 지역 번호", required = true,
            value = "upperLocalNo", example = "1")
    Long upperLocalNo;

    @Builder
    public PostSelectFromUpLocalDTO(Long upperLocalNo) {
        this.upperLocalNo = upperLocalNo;
    }
}
