package com.example.Used_Inst_market.model.domain.board.picture;

import com.example.Used_Inst_market.model.domain.board.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PICTURE")
public class Picture {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PICTURE_NO")
    private Long pictureNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @NotBlank
    @Column(name = "ORIGINAL_FILE_NAME", nullable = false)
    private String originalFileName;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "PATH", nullable = false)
    private String path;

    @Min(0)
    @Column(name = "SIZE", nullable = false)
    private Long size;

    @Builder
    public Picture(Post post, String originalFileName,
                   String name, String path, Long size) {
        this.post = post;
        this.originalFileName = originalFileName;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public void update(String originalFileName, String name,
                       String path, Long size) {
        this.originalFileName = originalFileName;
        this.name = name;
        this.path = path;
        this.size = size;
    }
}
