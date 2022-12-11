package com.example.Used_Inst_market.model.vo.local;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class LowerLocalVO {
    private Long lowerLocalNo;
    private String name;
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
