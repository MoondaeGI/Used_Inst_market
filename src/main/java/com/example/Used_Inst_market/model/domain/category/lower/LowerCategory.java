package com.example.Used_Inst_market.model.domain.category.lower;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.model.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_LOWER_CT")
public class LowerCategory extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_LOWER_CT_NO")
    private Long lowerCategoryNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_UPPER_CT_NO", nullable = false)
    private UpperCategory upperCategory;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public LowerCategory(UpperCategory upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
    }

    @OneToMany(mappedBy = "lowerCategory", orphanRemoval = true)
    private List<Brand> brands = new ArrayList<>();

    @OneToMany(mappedBy = "lowerCategory", orphanRemoval = true)
    private List<CategorySelect> categorySelects = new ArrayList<CategorySelect>();

    public void update(UpperCategory upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
    }
}
