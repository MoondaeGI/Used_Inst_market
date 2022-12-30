package com.example.Used_Inst_market.web.controller.index;

import com.example.Used_Inst_market.service.board.BoardService;
import com.example.Used_Inst_market.service.category.CategoryService;
import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.util.config.auth.dto.SessionUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = {"페이지 API"})
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final LocalService localService;
    private final HttpSession httpSession;

    @ApiOperation(value = "메인 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String index(@Valid Model model) {
        model.addAttribute("posts", boardService.selectAll());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @ApiOperation(value = "게시글 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @ApiOperation(value = "게시글 등록 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post/save")
    public String postSave(@Valid Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user.getName());
        }

        model.addAttribute("upper-category", categoryService.upperCategorySelectAll());
        model.addAttribute("lower-category", categoryService.lowerCategorySelectAll());
        model.addAttribute("brand", categoryService.brandSelectAll());
        model.addAttribute("upper-local", localService.upperLocalSelectAll());
        model.addAttribute("lower-local", localService.lowerLocalSelectAll());

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
