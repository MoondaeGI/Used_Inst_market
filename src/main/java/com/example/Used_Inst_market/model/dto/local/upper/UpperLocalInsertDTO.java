package com.example.Used_Inst_market.model.dto.local.upper;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpperLocalInsertDTO {
    @ApiParam(name = "상위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;
}
