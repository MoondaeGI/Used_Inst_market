package com.example.Used_Inst_market.web.controller.index;

import com.example.Used_Inst_market.model.vo.user.UserVO;
import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.service.category.CategoryService;
import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.board.picture.PictureSelectByPostDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Api(tags = {"페이지 API"})
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final BoardService boardService;
    private final PostService postService;
    private final PictureService pictureService;
    private final CategoryService categoryService;
    private final LocalService localService;
    private final HttpSession httpSession;

    @ApiOperation(value = "메인 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String index(@Valid Model model) {
        UserVO user = (UserVO) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("posts", boardService.selectAll());
        model.addAttribute("upper-category", categoryService.upperCategorySelectAll());
        model.addAttribute("upper-local", localService.upperLocalSelectAll());

        return "index";
    }

    @ApiOperation(value = "게시글 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post")
    public String post(@RequestParam("no") Long postNo, @Valid Model model) throws IOException {
        UserVO user = (UserVO) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        PostSelectDTO postSelectDTO = PostSelectDTO.builder()
                .postNo(postNo)
                .build();

        PictureSelectByPostDTO pictureSelectByPostDTO = PictureSelectByPostDTO.builder()
                .postNo(postNo)
                .build();

        model.addAttribute("post", postService.select(postSelectDTO));
        model.addAttribute("images", pictureService.selectByPost(pictureSelectByPostDTO));

        return "post";
    }

    @ApiOperation(value = "게시글 등록 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post/save")
    public String postSave(@Valid Model model) {
        UserVO user = (UserVO) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("upper-category", categoryService.upperCategorySelectAll());
        model.addAttribute("upper-local", localService.upperLocalSelectAll());

        return "post-save";
    }

    @ApiOperation(value = "게시글 수정 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post/update")
    public String postUpdate() { return "post-update"; }

    @ApiOperation(value = "검색 결과 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public String search() {
        return "search";
    }
}
