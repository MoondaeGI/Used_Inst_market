package com.example.Used_Inst_market.model.domain.category.brand;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.search.Search;
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
@Table(name = "TB_BRAND")
public class Brand extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_NO")
    private Long brandNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_LOWER_CT_NO", nullable = false)
    private LowerCategory lowerCategory;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Brand(LowerCategory lowerCategory, String name) {
        this.lowerCategory = lowerCategory;
        this.name = name;
    }

    @OneToMany(mappedBy = "brand", orphanRemoval = true)
    private List<Search> searches = new ArrayList<>();

    public void update(LowerCategory lowerCategory, String name) {
        this.lowerCategory = lowerCategory;
        this.name = name;
    }
}
