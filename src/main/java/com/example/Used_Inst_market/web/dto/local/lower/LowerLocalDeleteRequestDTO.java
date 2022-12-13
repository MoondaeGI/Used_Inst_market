package com.example.Used_Inst_market.web.dto.local.lower;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalDeleteRequestDTO {
    @ApiParam(name = "하위 지역 번호", required = true,
            value = "lowerLocalNo", example = "1")
    private Long lowerLocalNo;

    @Builder
    public LowerLocalDeleteRequestDTO(Long lowerLocalNo) {
        this.lowerLocalNo = lowerLocalNo;
    }
}
