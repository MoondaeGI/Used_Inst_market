package com.example.Used_Inst_market.service.board;

import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final PostRepository postRepository;
}
