package com.example.Used_Inst_market.web.dto.post;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostInsertRequestDTO {
    private User user;
    private String title;
    private String content;
    private Integer price;
    private UpperCategory upperCategory;
    private LowerCategory lowerCategory;
    private Brand brand;

    @Builder
    public PostInsertRequestDTO(
            User user, String title, String content, Integer price,
            UpperCategory upperCategory, LowerCategory lowerCategory, Brand brand) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.upperCategory = upperCategory;
        this.lowerCategory = lowerCategory;
        this.brand = brand;
    }

    public Post toEntity() {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .price(price)
                .build();
    }
}
