package com.example.Used_Inst_market.web.controller.local;

import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalDeleteDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalSelectDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalUpdateDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalDeleteDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalSelectDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateDTO;
import com.example.Used_Inst_market.model.vo.local.LowerLocalVO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"지역 API"})
@RequiredArgsConstructor
@RequestMapping("/local")
@RestController
public class LocalController {
    private final LocalService localService;

    @ApiOperation(value = "상위 지역 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/upper/info")
    public UpperLocalVO upperLocalSelect(
            @RequestParam(name = "no") Long upperLocalNo) {
        UpperLocalSelectDTO upperLocalSelectDTO = UpperLocalSelectDTO.builder()
                .upperLocalNo(upperLocalNo)
                .build();

        return localService.upperLocalSelect(upperLocalSelectDTO);
    }

    @ApiOperation(value = "상위 지역 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/upper/info/list")
    public List<UpperLocalVO> upperLocalSelectAll() {
        return localService.upperLocalSelectAll();
    }

    @ApiOperation(value = "상위 지역 정보 삽입 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/info")
    public Long upperLocalInsert(
            @RequestBody @Valid UpperLocalInsertDTO upperLocalInsertDTO) {
        return localService.upperLocalInsert(upperLocalInsertDTO);
    }

    @ApiOperation(value = "상위 지역 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/upper/info")
    public Long upperLocalUpdate(
            @RequestBody @Valid UpperLocalUpdateDTO upperLocalUpdateDTO) {
        return localService.upperLocalUpdate(upperLocalUpdateDTO);
    }

    @ApiOperation(value = "상위 지역 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/upper/info")
    public void upperLocalDelete(@RequestParam(name = "no") Long upperLocalNo) {
        UpperLocalDeleteDTO upperLocalDeleteDTO = UpperLocalDeleteDTO.builder()
                .upperLocalNo(upperLocalNo)
                .build();

        localService.upperLocalDelete(upperLocalDeleteDTO);
    }

    @ApiOperation(value = "하위 지역 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info")
    public LowerLocalVO lowerLocalSelect(@RequestParam("no") Long lowerLocalNo) {
        LowerLocalSelectDTO lowerLocalSelectDTO = LowerLocalSelectDTO.builder()
                .lowerLocalNo(lowerLocalNo)
                .build();

        return localService.lowerLocalSelect(lowerLocalSelectDTO);
    }

    @ApiOperation(value = "하위 지역 리스트 정보 조회 API")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lower/info/list")
    public List<LowerLocalVO> lowerLocalSelectAll() {
        return localService.lowerLocalSelectAll();
    }

    @ApiOperation(value = "하위 지역 정보 삽입 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lower/info")
    public Long lowerLocalInsert(
            @RequestBody @Valid LowerLocalInsertDTO lowerLocalInsertDTO) {
        return localService.lowerLocalInsert(lowerLocalInsertDTO);
    }

    @ApiOperation(value = "하위 지역 정보 수정 API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/lower/info")
    public Long lowerLocalUpdate(
            @RequestBody @Valid LowerLocalUpdateDTO lowerLocalUpdateDTO) {
        return localService.lowerLocalUpdate(lowerLocalUpdateDTO);
    }

    @ApiOperation(value = "하위 지역 정보 삭제 API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/lower/info")
    public void lowerLocalDelete(@RequestParam("no") Long lowerLocalNo) {
        LowerLocalDeleteDTO lowerLocalDeleteDTO = LowerLocalDeleteDTO.builder()
                .lowerLocalNo(lowerLocalNo)
                .build();

        localService.lowerLocalDelete(lowerLocalDeleteDTO);
    }
}
