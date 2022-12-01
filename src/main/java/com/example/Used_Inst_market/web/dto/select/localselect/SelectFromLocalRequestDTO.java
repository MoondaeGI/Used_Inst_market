package com.example.Used_Inst_market.web.dto.select.localselect;

import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromLocalRequestDTO {
    Local local;

    @Builder
    public SelectFromLocalRequestDTO(Local local) {
        this.local = local;
    }
}
