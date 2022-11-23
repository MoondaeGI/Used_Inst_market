package com.example.Used_Inst_market.domain.select.category;

import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_CT_SELECT")
public class CategorySelect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_CT_SELECT_NO")
    private Long categorySelectNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_NO", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_UPPER_CT_NO", nullable = false)
    private UpperCategory upperCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_LOWER_CT_NO", nullable = false)
    private LowerCategory lowerCategory;
}
