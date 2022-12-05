package com.example.Used_Inst_market.web.dto.city;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CitySelectRequestDTO {
    private Long cityNo;

    @Builder
    public CitySelectRequestDTO(Long cityNo) {
        this.cityNo = cityNo;
    }
}
