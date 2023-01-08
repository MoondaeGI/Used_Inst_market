package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PictureVO {
    @ApiParam(name = "이미지 번호", required = true, value = "pictureNo", example = "1")
    private Long pictureNo;
    @ApiParam(name = "게시글", required = true, value = "post")
    private PostVO post;
    @ApiParam(name = "원본 파일 이름", required = true, value = "originalFileName", example = "example.jpg")
    private String originalFileName;
    @ApiParam(name = "이미지 이름", required = true, value = "name", example = "images/example.jpg")
    private String name;
    @ApiParam(name = "이미지 경로", required = true)
    private String path;
    @ApiParam(name = "이미지 크기", required = true, value = "size", example = "1")
    private Long size;
    @ApiParam(name = "이미지 바이트 리스트", required = true, value = "imageByteArray", example = "[1, 1, 1, 1]")
    private byte[] imageByteArray;

    public PictureVO(Picture picture, byte[] imageByteArray) {
        this.pictureNo = picture.getPictureNo();
        this.post = PostVO.from(picture.getPost());
        this.originalFileName = picture.getOriginalFileName();
        this.name = picture.getName();
        this.path = picture.getPath();
        this.size = picture.getSize();

        this.imageByteArray = imageByteArray;
    }

    public static PictureVO of(Picture picture, byte[] imageByteArray) {
        return new PictureVO(picture, imageByteArray);
    }
}
