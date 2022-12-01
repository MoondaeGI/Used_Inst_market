package com.example.Used_Inst_market.web.dto.select.localselect;

import com.example.Used_Inst_market.domain.address.city.City;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromCityRequestDTO {
    private City city;

    @Builder
    public SelectFromCityRequestDTO(City city) {
        this.city = city;
    }
}
