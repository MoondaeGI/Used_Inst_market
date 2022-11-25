package com.example.Used_Inst_market.domain.category.upper;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.select.category.CategorySelect;
import com.example.Used_Inst_market.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public UpperCategory(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "upperCategory")
    private List<LowerCategory> lowerCategories = new ArrayList<LowerCategory>();

    @OneToMany(mappedBy = "upperCategory")
    private List<CategorySelect> categorySelects = new ArrayList<CategorySelect>();

    public void update(String name) { this.name = name; }
}
