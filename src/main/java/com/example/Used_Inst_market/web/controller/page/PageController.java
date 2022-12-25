package com.example.Used_Inst_market.web.controller.page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = {"페이지 API"})
@Controller
public class PageController {
    @ApiOperation(value = "메인 페이지 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String index() {
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
    public String postSave() { return "post-save"; }

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
