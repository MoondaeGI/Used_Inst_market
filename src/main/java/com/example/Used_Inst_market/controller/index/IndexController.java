package com.example.Used_Inst_market.controller.index;

import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.dto.board.searching.PostSearchSelectDTO;
import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.model.vo.user.UserVO;
import com.example.Used_Inst_market.service.board.CommentService;
import com.example.Used_Inst_market.service.board.PictureService;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.service.category.CategoryService;
import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.util.config.auth.SessionUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = {"페이지 API"})
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostService postService;
    private final PictureService pictureService;
    private final CommentService commentService;
    private final CategoryService categoryService;
    private final LocalService localService;
    private final HttpSession httpSession;

    @ApiOperation(value = "메인 페이지 조회 API")
    @GetMapping("/")
    public String index(@Valid Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) model.addAttribute("user", user);
        
        model.addAttribute("posts", postService.selectAll());
        model.addAttribute("upper-category", categoryService.upperCategorySelectAll());
        model.addAttribute("upper-local", localService.upperLocalSelectAll());

        return "index";
    }

    @ApiOperation(value = "게시글 페이지 조회 API")
    @GetMapping("/post/page")
    public String post(
            @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
            @RequestParam("no") Long postNo,
            @Valid Model model) throws IOException {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) model.addAttribute("user", user);

        PostVO post = postService.select(postNo);
        model.addAttribute("post", post);
        model.addAttribute("soldYN", post.getSoldYN());

        model.addAttribute("comments", commentService.selectFromPost(postNo));
        setup(postNo, model);

        return "post";
    }

    @ApiOperation(value = "게시글 등록 페이지 조회 API")
    @GetMapping("/post/save/page")
    public String postSave(@Valid Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) model.addAttribute("user", user);

        model.addAttribute("upper-category", categoryService.upperCategorySelectAll());
        model.addAttribute("upper-local", localService.upperLocalSelectAll());

        return "post-save";
    }

    @ApiOperation(value = "게시글 수정 페이지 조회 API")
    @GetMapping("/post/update/page")
    public String postUpdate(
            @ApiParam(name = "게시글 번호", required = true, value = "postNo", example = "1")
            @RequestParam("no") Long postNo,
            @Valid Model model) throws IOException {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) model.addAttribute("user", user);

        model.addAttribute("upper-category", categoryService.upperCategorySelectAll());
        model.addAttribute("upper-local", localService.upperLocalSelectAll());
        model.addAttribute("post", postService.select(postNo));

        setup(postNo, model);

        return "post-update";
    }

    @ApiOperation(value = "검색 결과 페이지 조회 API")
    @PostMapping("/search/page")
    public String search(@RequestBody PostSearchSelectDTO postSearchSelectDTO, @Valid Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) model.addAttribute("user", user);

        model.addAttribute("posts", postService.selectFromSearchingKey(postSearchSelectDTO));

        return "search";
    }

    private void setup(Long postNo, Model model) throws IOException {
        List<PictureVO> posts = pictureService.selectByPost(postNo);
        if (!posts.isEmpty()) {
            model.addAttribute("images", posts);
        }
    }
}
