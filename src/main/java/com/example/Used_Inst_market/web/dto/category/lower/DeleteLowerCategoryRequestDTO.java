package com.example.Used_Inst_market.web.dto.category.lower;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteLowerCategoryRequestDTO {
    private Long lowerCategoryNo;

    @Builder
    public DeleteLowerCategoryRequestDTO(Long lowerCategoryNo) {
        this.lowerCategoryNo = lowerCategoryNo;
    }
}
