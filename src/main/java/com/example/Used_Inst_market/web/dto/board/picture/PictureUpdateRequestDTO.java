package com.example.Used_Inst_market.web.dto.board.picture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class PictureUpdateRequestDTO {
    private Long postNo;
    private List<MultipartFile> multipartFiles;

    @Builder
    public PictureUpdateRequestDTO(
            Long postNo, List<MultipartFile> multipartFiles) {
        this.postNo = postNo;
        this.multipartFiles = multipartFiles;
    }
}
