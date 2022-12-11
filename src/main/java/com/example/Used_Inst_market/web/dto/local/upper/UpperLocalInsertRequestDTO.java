package com.example.Used_Inst_market.web.dto.local.upper;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalInsertRequestDTO {
    private String name;

    @Builder
    public UpperLocalInsertRequestDTO(String name) {
        this.name = name;
    }

    public UpperLocal toEntity() {
        return UpperLocal.builder()
                .name(name)
                .build();
    }
}
