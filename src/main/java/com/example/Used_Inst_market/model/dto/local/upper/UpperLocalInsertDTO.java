package com.example.Used_Inst_market.model.dto.local.upper;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpperLocalInsertDTO {
    @ApiParam(name = "상위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;

    @Builder
    public UpperLocalInsertDTO(String name) {
        this.name = name;
    }

    public UpperLocal toEntity() {
        return UpperLocal.builder()
                .name(name)
                .build();
    }
}
