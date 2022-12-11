package com.example.Used_Inst_market.web.dto.local.lower;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LowerLocalUpdateRequestDTO {
    private Long cityNo;
    private String name;
    private UpperLocal upperLocal;

    @Builder
    public LowerLocalUpdateRequestDTO(Long cityNo, String name, UpperLocal upperLocal) {
        this.cityNo = cityNo;
        this.name = name;
        this.upperLocal = upperLocal;
    }
}
