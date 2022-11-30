package com.example.Used_Inst_market.web.dto.category.categoryselect;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectedAsUpperRequestDTO {
    UpperCategory upperCategory;

    @Builder
    public SelectedAsUpperRequestDTO(UpperCategory upperCategory) {
        this.upperCategory = upperCategory;
    }
}
