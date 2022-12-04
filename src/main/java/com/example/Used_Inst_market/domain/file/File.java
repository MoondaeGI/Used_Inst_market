package com.example.Used_Inst_market.domain.file;

import com.example.Used_Inst_market.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_FILE")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_NO")
    private Long fileNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATH")
    private String path;

    @Column(name = "SIZE")
    private Long size;
}
