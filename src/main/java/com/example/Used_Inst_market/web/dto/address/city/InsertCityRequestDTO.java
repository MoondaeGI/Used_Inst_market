package com.example.Used_Inst_market.web.dto.address.city;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InsertCityRequestDTO {
    private String name;
    private Local local;

    @Builder
    public InsertCityRequestDTO(String name, Local local) {
        this.local = local;
        this.name = name;
    }

    public City toEntity() {
        return City.builder()
                .name(name)
                .local(local)
                .build();
    }
}
