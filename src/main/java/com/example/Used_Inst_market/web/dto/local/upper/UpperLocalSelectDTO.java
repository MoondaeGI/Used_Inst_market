package com.example.Used_Inst_market.web.dto.local.upper;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalSelectDTO {
    @ApiParam(name = "상위 지역 번호", required = true,
            value = "upperLocalNo", example = "1")
    private Long upperLocalNo;

    @Builder
    public UpperLocalSelectDTO(Long upperLocalNo) {
        this.upperLocalNo = upperLocalNo;
    }
}
