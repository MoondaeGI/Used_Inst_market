package com.example.Used_Inst_market.web.controller.local;

import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.local.LowerLocalVO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"지역 API"})
@RequiredArgsConstructor
@RequestMapping("/local")
@RestController
public class LocalController {
    private final LocalService localService;

    @ApiOperation(value = "상위 지역 정보 조회 API")
    @GetMapping(value = "/upper/info")
    public UpperLocalVO upperLocalSelect(
            @RequestParam(name = "no") Long upperLocalNo) {
        UpperLocalSelectRequestDTO upperLocalSelectRequestDTO =
                UpperLocalSelectRequestDTO.builder()
                        .upperLocalNo(upperLocalNo)
                        .build();

        return localService.upperLocalSelect(upperLocalSelectRequestDTO);
    }

    @ApiOperation(value = "상위 지역 리스트 정보 조회 API")
    @GetMapping(value = "/upper/info/list")
    public List<UpperLocalVO> upperLocalSelectAll() {
        return localService.upperLocalSelectAll();
    }

    @ApiOperation(value = "상위 지역 정보 삽입 API")
    @PostMapping(value = "/info")
    public Long upperLocalInsert(
            @RequestBody UpperLocalInsertRequestDTO upperLocalInsertRequestDTO) {
        return localService.upperLocalInsert(upperLocalInsertRequestDTO);
    }

    @ApiOperation(value = "상위 지역 정보 수정 API")
    @PutMapping(value = "/upper/info")
    public Long upperLocalUpdate(
            @RequestBody UpperLocalUpdateRequestDTO upperLocalUpdateRequestDTO) {
        return localService.upperLocalUpdate(upperLocalUpdateRequestDTO);
    }

    @ApiOperation(value = "상위 지역 삭제 API")
    @DeleteMapping(value = "/upper/info")
    public void upperLocalDelete(@RequestParam(name = "no") Long upperLocalNo) {
        UpperLocalDeleteRequestDTO upperLocalDeleteRequestDTO =
                UpperLocalDeleteRequestDTO.builder()
                        .upperLocalNo(upperLocalNo)
                        .build();

        localService.upperLocalDelete(upperLocalDeleteRequestDTO);
    }

    @ApiOperation(value = "하위 지역 정보 조회 API")
    @GetMapping("/lower/info")
    public LowerLocalVO lowerLocalSelect(@RequestParam("no") Long lowerLocalNo) {
        LowerLocalSelectRequestDTO lowerLocalSelectRequestDTO =
                LowerLocalSelectRequestDTO.builder()
                        .lowerLocalNo(lowerLocalNo)
                        .build();

        return localService.lowerLocalSelect(lowerLocalSelectRequestDTO);
    }

    @ApiOperation(value = "하위 지역 리스트 정보 조회 API")
    @GetMapping("/lower/info/list")
    public List<LowerLocalVO> lowerLocalSelectAll() {
        return localService.lowerLocalSelectAll();
    }

    @ApiOperation(value = "하위 지역 정보 삽입 API")
    @PostMapping("/lower/info")
    public Long lowerLocalInsert(
            @RequestBody LowerLocalInsertRequestDTO lowerLocalInsertRequestDTO) {
        return localService.lowerLocalInsert(lowerLocalInsertRequestDTO);
    }

    @ApiOperation(value = "하위 지역 정보 수정 API")
    @PutMapping("/lower/info")
    public Long lowerLocalUpdate(
            @RequestBody LowerLocalUpdateRequestDTO lowerLocalUpdateRequestDTO) {
        return localService.lowerLocalUpdate(lowerLocalUpdateRequestDTO);
    }

    @ApiOperation(value = "하위 지역 정보 삭제 API")
    @DeleteMapping("/lower/info")
    public void lowerLocalDelete(@RequestParam("no") Long lowerLocalNo) {
        LowerLocalDeleteRequestDTO lowerLocalDeleteRequestDTO =
                LowerLocalDeleteRequestDTO.builder()
                        .lowerLocalNo(lowerLocalNo)
                        .build();

        localService.lowerLocalDelete(lowerLocalDeleteRequestDTO);
    }
}
