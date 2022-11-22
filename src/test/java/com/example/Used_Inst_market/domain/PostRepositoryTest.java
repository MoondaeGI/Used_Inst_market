package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.PostRepository;
import com.example.Used_Inst_market.domain.post.SoldYN;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @After
    public void teardown() {
        postRepository.deleteAll();
    }

    @Test
    public void TB_POST_추가() {
        String testTitle = "title";
        String testContent = "content";

        Post post = Post.builder()
                .title(testTitle)
                .content(testContent)
                .build();

        postRepository.save(post);
        List<Post> posts = postRepository.findAll();

        assertThat(posts.get(0).getTitle()).isEqualTo("title");
        assertThat(posts.get(0).getContent()).isEqualTo("content");
        assertThat(posts.get(0).getSoldYN()).isEqualTo(SoldYN.SALE);
    }
}
