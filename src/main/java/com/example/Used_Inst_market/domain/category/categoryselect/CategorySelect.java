package com.example.Used_Inst_market.domain.category.categoryselect;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_CT_SELECT")
public class CategorySelect {
    @Id
    @Column(name = "PD_CT_SELECT_NO")
    private Long categorySelectNo;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_UPPER_CT_NO", nullable = false)
    private UpperCategory upperCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PD_LOWER_CT_NO", nullable = false)
    private LowerCategory lowerCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_NO", nullable = false)
    private Brand brand;

    @Builder
    public CategorySelect(Post post, UpperCategory upperCategory,
                          LowerCategory lowerCategory, Brand brand) {
        this.post = post;
        this.upperCategory = upperCategory;
        this.lowerCategory = lowerCategory;
        this.brand = brand;
    }

    public void update(UpperCategory upperCategory,
                       LowerCategory lowerCategory, Brand brand) {
        this.upperCategory = upperCategory;
        this.lowerCategory = lowerCategory;
        this.brand = brand;
    }
}
