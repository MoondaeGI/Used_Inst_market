package com.example.Used_Inst_market.model.vo.local;

import com.example.Used_Inst_market.model.domain.city.City;
import com.example.Used_Inst_market.model.domain.local.Local;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class CityVO {
    private Long cityNo;
    private String name;
    private Local local;

    public CityVO(City city) {
        this.cityNo = city.getCityNo();
        this.name = city.getName();
        this.local = city.getLocal();
    }

    public static CityVO from(City city) {
        return new CityVO(city);
    }
}
