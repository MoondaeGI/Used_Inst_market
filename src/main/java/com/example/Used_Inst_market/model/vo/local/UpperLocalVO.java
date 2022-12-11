package com.example.Used_Inst_market.model.vo.local;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpperLocalVO {
    private Long upperLocalNo;
    private String name;

    public UpperLocalVO(UpperLocal upperLocal) {
        this.upperLocalNo = upperLocal.getUpperLocalNo();
        this.name = upperLocal.getName();
    }

    public static UpperLocalVO from(UpperLocal upperLocal) {
        return new UpperLocalVO(upperLocal);
    }
}
