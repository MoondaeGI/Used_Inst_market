package com.example.Used_Inst_market.web.dto.board.picture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class PictureInsertRequestDTO {
    private Long postNo;
    private List<MultipartFile> multipartFiles;

    @Builder
    public PictureInsertRequestDTO(
            Long postNo, List<MultipartFile> multipartFiles) {
        this.postNo = postNo;
        this.multipartFiles = multipartFiles;
    }
}
