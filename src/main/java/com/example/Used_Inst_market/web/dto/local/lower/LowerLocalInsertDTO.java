package com.example.Used_Inst_market.web.dto.local.lower;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalInsertDTO {
    @ApiParam(name = "상위 지역", required = true)
    private UpperLocal upperLocal;
    @ApiParam(name = "하위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public LowerLocalInsertDTO(String name, UpperLocal upperLocal) {
        this.upperLocal = upperLocal;
        this.name = name;
    }

    public LowerLocal toEntity() {
        return LowerLocal.builder()
                .name(name)
                .upperLocal(upperLocal)
                .build();
    }
}
