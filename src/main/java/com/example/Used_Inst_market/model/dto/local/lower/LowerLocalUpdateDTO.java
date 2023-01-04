package com.example.Used_Inst_market.model.dto.local.lower;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalUpdateDTO {
    @ApiParam(name = "하위 지역 번호", required = true,
            value = "lowerLocalNo", example = "1")
    private Long lowerLocalNo;
    @ApiParam(name = "하위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;
    @ApiParam(name = "상위 지역", required = true)
    private UpperLocal upperLocal;

    @Builder
    public LowerLocalUpdateDTO(Long lowerLocalNo, String name, UpperLocal upperLocal) {
        this.lowerLocalNo = lowerLocalNo;
        this.name = name;
        this.upperLocal = upperLocal;
    }
}
