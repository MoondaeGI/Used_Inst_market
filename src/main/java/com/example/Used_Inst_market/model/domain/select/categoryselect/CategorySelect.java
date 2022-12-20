package com.example.Used_Inst_market.model.domain.select.categoryselect;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_CT_SELECT")
public class CategorySelect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_CT_SELECT_NO")
    private Long categorySelectNo;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PD_UPPER_CT_NO")
    private UpperCategory upperCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PD_LOWER_CT_NO")
    private LowerCategory lowerCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BRAND_NO")
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
