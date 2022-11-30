package com.example.Used_Inst_market.web.dto.category.categoryselect;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectedAsLowerRequestDTO {
    private LowerCategory lowerCategory;

    @Builder
    public SelectedAsLowerRequestDTO(LowerCategory lowerCategory) {
        this.lowerCategory = lowerCategory;
    }
}
