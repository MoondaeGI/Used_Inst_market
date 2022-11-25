package com.example.Used_Inst_market.domain.brand;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.product.Product;
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
@Table(name = "TB_BRAND")
public class Brand extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_NO")
    private Long brandNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_LOWER_CT_NO", nullable = false)
    private LowerCategory lowerCategory;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Brand(LowerCategory lowerCategory, String name) {
        this.lowerCategory = lowerCategory;
        this.name = name;
    }

    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<Product>();

    public void update(LowerCategory lowerCategory, String name) {
        this.lowerCategory = lowerCategory;
        this.name = name;
    }
}
