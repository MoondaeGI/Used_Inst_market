package com.example.Used_Inst_market.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSelectRequestDTO {
    private Long postNo;

    @Builder
    public PostSelectRequestDTO(Long postNo) {
        this.postNo = postNo;
    }
}
