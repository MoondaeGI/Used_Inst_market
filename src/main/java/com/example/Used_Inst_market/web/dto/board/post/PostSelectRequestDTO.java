package com.example.Used_Inst_market.web.dto.board.post;

import com.example.Used_Inst_market.model.vo.board.PictureVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostSelectRequestDTO {
    private Long postNo;

    @Builder
    public PostSelectRequestDTO(Long postNo) {
        this.postNo = postNo;
    }
}
