package com.example.Used_Inst_market.web.dto.local.upper;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalUpdateRequestDTO {
    @ApiParam(name = "상위 지역 번호", required = true,
            value = "upperLocalNo", example = "1")
    private Long upperLocalNo;
    @ApiParam(name = "상위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public UpperLocalUpdateRequestDTO(Long upperLocalNo, String name) {
        this.upperLocalNo = upperLocalNo;
        this.name = name;
    }
}
