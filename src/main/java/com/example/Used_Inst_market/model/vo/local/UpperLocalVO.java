package com.example.Used_Inst_market.model.vo.local;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalVO {
    @ApiParam(name = "상위 지역 번호", required = true,
            value = "upperLocalNo", example = "1")
    private Long upperLocalNo;
    @ApiParam(name = "상위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;

    public UpperLocalVO(UpperLocal upperLocal) {
        this.upperLocalNo = upperLocal.getUpperLocalNo();
        this.name = upperLocal.getName();
    }

    public static UpperLocalVO from(UpperLocal upperLocal) {
        return new UpperLocalVO(upperLocal);
    }
}
