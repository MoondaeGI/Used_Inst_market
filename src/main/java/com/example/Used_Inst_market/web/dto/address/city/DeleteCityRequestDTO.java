package com.example.Used_Inst_market.web.dto.address.city;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteCityRequestDTO {
    private Long cityNo;

    @Builder
    public DeleteCityRequestDTO(Long cityNo) {
        this.cityNo = cityNo;
    }
}
