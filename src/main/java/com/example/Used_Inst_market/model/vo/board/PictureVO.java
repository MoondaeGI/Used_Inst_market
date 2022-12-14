package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PictureVO {
    @ApiParam(name = "이미지 번호", required = true,
            value = "pictureNo", example = "1")
    private Long pictureNo;
    @ApiParam(name = "게시글", required = true)
    private Post post;
    @ApiParam(name = "원본 파일 이름", required = true,
            value = "originalFileName", example = "example.jpg")
    private String originalFileName;
    @ApiParam(name = "이미지 이름", required = true,
            value = "name", example = "/example20221214.jpg")
    private String name;
    @ApiParam(name = "이미지 경로", required = true)
    private String path;
    @ApiParam(name = "이미지 크기", required = false,
            value = "size", example = "1")
    private Long size;

    public PictureVO(Picture picture) {
        this.pictureNo = picture.getPictureNo();
        this.post = picture.getPost();
        this.originalFileName = picture.getOriginalFileName();
        this.name = picture.getName();
        this.path = picture.getPath();
        this.size = picture.getSize();
    }

    public static PictureVO from(Picture picture) {
        return new PictureVO(picture);
    }
}
