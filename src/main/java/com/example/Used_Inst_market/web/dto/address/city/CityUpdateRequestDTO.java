package com.example.Used_Inst_market.web.dto.address.city;

import com.example.Used_Inst_market.domain.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityUpdateRequestDTO {
    private Long cityNo;
    private String name;
    private Local local;

    @Builder
    public CityUpdateRequestDTO(Long cityNo, String name, Local local) {
        this.cityNo = cityNo;
        this.name = name;
        this.local = local;
    }
}
