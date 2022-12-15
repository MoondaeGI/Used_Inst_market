package com.example.Used_Inst_market.web.dto.board.picture;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class PictureUpdateRequestDTO {
    @ApiParam(name = "게시글 번호", required = true,
            value = "postNo", example = "1")
    private Long postNo;
    @ApiParam(name = "이미지 파일 리스트", required = true,
            value = "multipartFiles", example = "[1, 1, 1, 1]")
    private List<MultipartFile> multipartFiles;

    @Builder
    public PictureUpdateRequestDTO(
            Long postNO, List<MultipartFile> multipartFiles) {
        this.postNo = postNO;
        this.multipartFiles = multipartFiles;
    }
}
