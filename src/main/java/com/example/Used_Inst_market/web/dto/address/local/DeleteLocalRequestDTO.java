package com.example.Used_Inst_market.web.dto.address.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteLocalRequestDTO {
    private Long localNo;

    @Builder
    public DeleteLocalRequestDTO(Long localNo) {
        this.localNo = localNo;
    }
}
