package com.example.Used_Inst_market.web.vo.address;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityInfoVO {
    private Long cityNo;
    private String name;
    private Local local;

    @Builder
    public CityInfoVO(City city) {
        this.cityNo = city.getCityNo();
        this.name = city.getName();
        this.local = city.getLocal();
    }
}
