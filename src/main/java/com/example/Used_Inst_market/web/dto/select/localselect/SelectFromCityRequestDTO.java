package com.example.Used_Inst_market.web.dto.select.localselect;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromCityRequestDTO {
    private Long cityNo;

    @Builder
    public SelectFromCityRequestDTO(Long cityNo) {
        this.cityNo = cityNo;
    }
}
