package com.example.Used_Inst_market.web.dto.address.local;

import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalResponseDTO {
    private Long localNo;
    private String name;

    @Builder
    public LocalResponseDTO(Local local) {
        this.localNo = local.getLocalNo();
        this.name = local.getName();
    }
}
