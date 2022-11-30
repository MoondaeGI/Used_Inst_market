package com.example.Used_Inst_market.web.dto.post;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.post.SoldYN;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDTO {
    private Long postNo;
    private String title;
    private String content;
    private Integer price;
    private SoldYN soldYN;
    private UpperCategory upperCategory;
    private LowerCategory lowerCategory;
    private Brand brand;

    @Builder
    public PostUpdateRequestDTO(Long postNo, String title, String content,
                                Integer price, SoldYN soldYN, UpperCategory upperCategory,
                                LowerCategory lowerCategory, Brand brand) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = soldYN;
        this.upperCategory = upperCategory;
        this.lowerCategory = lowerCategory;
        this.brand = brand;
    }
}
