package com.example.Used_Inst_market.web.dto.board.picture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureSelectRequestDTO {
    private Long postNo;

    @Builder
    public PictureSelectRequestDTO(Long postNo) {
        this.postNo = postNo;
    }
}
