package com.example.Used_Inst_market.web.dto.address.city;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityDeleteRequestDTO {
    private Long cityNo;

    @Builder
    public CityDeleteRequestDTO(Long cityNo) {
        this.cityNo = cityNo;
    }
}
