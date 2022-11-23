package com.example.Used_Inst_market.web.dto.address.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SelectLocalListRequestDTO {
    private Long localNo;
    private String name;
    private LocalDateTime regDt;
    private LocalDateTime modDt;

    @Builder
    public SelectLocalListRequestDTO(Long localNo, String name,
                                     LocalDateTime regDt, LocalDateTime modDt) {
        this.localNo = localNo;
        this.name = name;
        this.regDt = regDt;
        this.modDt = modDt;
    }
}
