package com.example.Used_Inst_market.web.dto.board.post;

import com.example.Used_Inst_market.web.vo.post.PictureVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostSelectRequestDTO {
    private Long postNo;
    private List<PictureVO> pictures;

    @Builder
    public PostSelectRequestDTO(Long postNo, List<PictureVO> pictures) {
        this.postNo = postNo;
        this.pictures = pictures;
    }
}
