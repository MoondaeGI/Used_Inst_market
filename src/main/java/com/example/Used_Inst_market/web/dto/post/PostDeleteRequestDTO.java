package com.example.Used_Inst_market.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDeleteRequestDTO {
    private Long postNo;

    @Builder
    public PostDeleteRequestDTO(Long postNo) {
        this.postNo = postNo;
    }
}
