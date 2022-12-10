package com.example.Used_Inst_market.web.dto.city;

import com.example.Used_Inst_market.model.domain.city.City;
import com.example.Used_Inst_market.model.domain.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityInsertRequestDTO {
    private String name;
    private Local local;

    @Builder
    public CityInsertRequestDTO(String name, Local local) {
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
