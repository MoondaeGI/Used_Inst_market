package com.example.Used_Inst_market.model.domain.select.localselect;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalSelectRepository extends JpaRepository<LocalSelect, Long> {
    LocalSelect findByPost(Post post);

    List<Post> findByUpperLocal(UpperLocal upperLocal);

    List<Post> findByLowerLocal(LowerLocal lowerLocal);
}