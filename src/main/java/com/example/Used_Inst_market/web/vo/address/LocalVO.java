package com.example.Used_Inst_market.web.vo.address;

import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalVO {
    private Long localNo;
    private String name;

    @Builder
    public LocalVO(Local local) {
        this.localNo = local.getLocalNo();
        this.name = local.getName();
    }
}
