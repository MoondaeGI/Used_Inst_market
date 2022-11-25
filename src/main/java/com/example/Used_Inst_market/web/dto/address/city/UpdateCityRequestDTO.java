package com.example.Used_Inst_market.web.dto.address.city;

import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCityRequestDTO {
    private Long cityNo;
    private String name;
    private Local local;

    @Builder
    public UpdateCityRequestDTO(Long cityNo, String name, Local local) {
        this.cityNo = cityNo;
        this.name = name;
        this.local = local;
    }
}
