package com.example.Used_Inst_market.model.domain.search;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_SEARCH")
public class Search {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEARCH_NO")
    private Long searchNo;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PD_UPPER_CT_NO")
    private UpperCategory upperCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PD_LOWER_CT_NO")
    private LowerCategory lowerCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BRAND_NO")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UPPER_LO_NO")
    private UpperLocal upperLocal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LOWER_LO_NO")
    private LowerLocal lowerLocal;

    @Builder
    public Search(Post post, UpperCategory upperCategory, LowerCategory lowerCategory, Brand brand,
                  UpperLocal upperLocal, LowerLocal lowerLocal) {
        this.post = post;
        this.upperCategory = upperCategory;
        this.lowerCategory = lowerCategory;
        this.brand = brand;
        this.upperLocal = upperLocal;
        this.lowerLocal = lowerLocal;
    }

    public void update(UpperCategory upperCategory, LowerCategory lowerCategory, Brand brand,
                       UpperLocal upperLocal, LowerLocal lowerLocal) {
        this.upperCategory = upperCategory;
        this.lowerCategory = lowerCategory;
        this.brand = brand;
        this.upperLocal = upperLocal;
        this.lowerLocal = lowerLocal;
    }
}
