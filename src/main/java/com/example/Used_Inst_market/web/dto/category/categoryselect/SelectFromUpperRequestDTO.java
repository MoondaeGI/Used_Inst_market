package com.example.Used_Inst_market.web.dto.category.categoryselect;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectFromUpperRequestDTO {
    UpperCategory upperCategory;

    @Builder
    public SelectFromUpperRequestDTO(UpperCategory upperCategory) {
        this.upperCategory = upperCategory;
    }
}
