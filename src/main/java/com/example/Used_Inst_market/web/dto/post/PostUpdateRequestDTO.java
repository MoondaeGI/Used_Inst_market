package com.example.Used_Inst_market.web.dto.post;

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
    private SoldYN soldYN;

    @Builder
    public PostUpdateRequestDTO(Long postNo, String title,
                                String content, SoldYN soldYN) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.soldYN = soldYN;
    }
}
