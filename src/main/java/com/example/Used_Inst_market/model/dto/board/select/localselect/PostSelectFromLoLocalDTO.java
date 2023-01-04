package com.example.Used_Inst_market.model.dto.board.select.localselect;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSelectFromLoLocalDTO {
    @ApiParam(name = "하위 지역 번호", required = true,
            value = "lowerLocalNo", example = "1")
    private Long lowerLocalNo;

    @Builder
    public PostSelectFromLoLocalDTO(Long lowerLocalNo) {
        this.lowerLocalNo = lowerLocalNo;
    }
}
