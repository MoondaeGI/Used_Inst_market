package com.example.Used_Inst_market.web.vo.address;

import com.example.Used_Inst_market.domain.local.Local;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalVO {
    private Long localNo;
    private String name;

    public LocalVO(Local local) {
        this.localNo = local.getLocalNo();
        this.name = local.getName();
    }

    public static LocalVO from(Local local) {
        return new LocalVO(local);
    }
}
