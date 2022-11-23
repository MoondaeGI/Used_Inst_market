package com.example.Used_Inst_market.domain.category.lower;

import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
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
@Table(name = "TB_PD_LOWER_CT")
public class LowerCategory extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_LOWER_CT_NO")
    private Long lowerCategoryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_UPPER_CT_NO", nullable = false)
    private UpperCategory upperCategory;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public LowerCategory(UpperCategory upperCategory, String name) {
        this.upperCategory = upperCategory;
        this.name = name;
    }

    @OneToMany(mappedBy = "lowerCategory")
    private List<CategorySelect> categorySelects = new ArrayList<CategorySelect>();
}
