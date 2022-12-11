package com.example.Used_Inst_market.web.dto.local.lower;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalInsertRequestDTO {
    private String name;
    private UpperLocal upperLocal;

    @Builder
    public LowerLocalInsertRequestDTO(String name, UpperLocal upperLocal) {
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
