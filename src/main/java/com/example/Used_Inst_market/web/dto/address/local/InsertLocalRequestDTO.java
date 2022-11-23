package com.example.Used_Inst_market.web.dto.address.local;

import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertLocalRequestDTO {
    private String name;

    @Builder
    public InsertLocalRequestDTO(String name) {
        this.name = name;
    }

    public Local toEntity() {
        return Local.builder()
                .name(name)
                .build();
    }
}
