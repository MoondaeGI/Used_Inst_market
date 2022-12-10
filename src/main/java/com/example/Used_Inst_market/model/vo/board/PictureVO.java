package com.example.Used_Inst_market.model.vo.board;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class PictureVO {
    private Long pictureNo;
    private Post post;
    private String originalFileName;
    private String name;
    private String path;
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
