package com.example.Used_Inst_market.web.dto.local;

import com.example.Used_Inst_market.domain.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalInsertRequestDTO {
    private String name;

    @Builder
    public LocalInsertRequestDTO(String name) {
        this.name = name;
    }

    public Local toEntity() {
        return Local.builder()
                .name(name)
                .build();
    }
}
