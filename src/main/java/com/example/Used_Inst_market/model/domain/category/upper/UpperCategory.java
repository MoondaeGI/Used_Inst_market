package com.example.Used_Inst_market.model.domain.category.upper;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.model.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_UPPER_CT")
public class UpperCategory extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_UPPER_CT_NO")
    private Long upperCategoryNo;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public UpperCategory(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "upperCategory", orphanRemoval = true)
    private List<LowerCategory> lowerCategories = new ArrayList<LowerCategory>();

    @OneToMany(mappedBy = "upperCategory", orphanRemoval = true)
    private List<CategorySelect> categorySelects = new ArrayList<CategorySelect>();

    public void update(String name) { this.name = name; }
}
