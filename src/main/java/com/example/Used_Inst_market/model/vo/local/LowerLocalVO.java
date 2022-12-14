package com.example.Used_Inst_market.model.vo.local;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class LowerLocalVO {
    @ApiParam(name = "하위 지역 번호", required = true,
            value = "lowerLocalNo", example = "1")
    private Long lowerLocalNo;
    @ApiParam(name = "하위 지역 이름", required = true,
            value = "name", example = "example")
    private String name;
    @ApiParam(name = "상위 지역", required = true)
    private UpperLocal upperLocal;

    public LowerLocalVO(LowerLocal lowerLocal) {
        this.lowerLocalNo = lowerLocal.getLowerLocalNo();
        this.name = lowerLocal.getName();
        this.upperLocal = lowerLocal.getUpperLocal();

    }

    public static LowerLocalVO from(LowerLocal lowerLocal) {
        return new LowerLocalVO(lowerLocal);
    }
}
