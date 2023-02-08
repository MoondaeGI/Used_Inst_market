package com.example.Used_Inst_market.controller.local;

import com.example.Used_Inst_market.model.dto.local.lower.LowerLocalInsertDTO;
import com.example.Used_Inst_market.model.dto.local.lower.LowerLocalUpdateDTO;
import com.example.Used_Inst_market.model.dto.local.upper.UpperLocalInsertDTO;
import com.example.Used_Inst_market.model.dto.local.upper.UpperLocalUpdateDTO;
import com.example.Used_Inst_market.model.vo.local.LowerLocalVO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
import com.example.Used_Inst_market.service.local.LocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
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
    @GetMapping(value = "/upper/info")
    public UpperLocalVO upperLocalSelect(
            @ApiParam(name = "상위 지역 번호", required = true, value = "upperLocalNo", example = "1")
            @RequestParam(name = "no") @Valid Long upperLocalNo) {
        return localService.upperLocalSelect(upperLocalNo);
    }

    @ApiOperation(value = "상위 지역 리스트 정보 조회 API")
    @GetMapping(value = "/upper/info/list")
    public List<UpperLocalVO> upperLocalSelectAll() {
        return localService.upperLocalSelectAll();
    }

    @ApiOperation(value = "상위 지역 정보 삽입 API")
    @PostMapping(value = "/upper/save")
    public Long upperLocalInsert(@RequestBody @Valid UpperLocalInsertDTO upperLocalInsertDTO) {
        return localService.upperLocalInsert(upperLocalInsertDTO);
    }

    @ApiOperation(value = "상위 지역 정보 수정 API")
    @PutMapping(value = "/upper/update")
    public Long upperLocalUpdate(@RequestBody @Valid UpperLocalUpdateDTO upperLocalUpdateDTO) {
        return localService.upperLocalUpdate(upperLocalUpdateDTO);
    }

    @ApiOperation(value = "상위 지역 삭제 API")
    @DeleteMapping(value = "/upper/delete")
    public void upperLocalDelete(
            @ApiParam(name = "상위 지역 번호", required = true, value = "upperLocalNo", example = "1")
            @RequestParam(name = "no") @Valid Long upperLocalNo) {
        localService.upperLocalDelete(upperLocalNo);
    }

    @ApiOperation(value = "하위 지역 정보 조회 API")
    @GetMapping("/lower/info")
    public LowerLocalVO lowerLocalSelect(
            @ApiParam(name = "하위 지역 번호", required = true, value = "lowerLocalNo", example = "1")
            @RequestParam("no") @Valid Long lowerLocalNo) {
        return localService.lowerLocalSelect(lowerLocalNo);
    }

    @ApiOperation(value = "상위 지역으로 하위 지역 리스트 정보 조회 API")
    @GetMapping("/lower/info/upper")
    public List<LowerLocalVO> lowerLocalSelectFromUpperLocal(
            @ApiParam(name = "상위 지역 번호", required = true, value = "upperLocalNo", example = "1")
            @RequestParam("no") @Valid Long upperLocalNo) {
        return localService.lowerLocalSelectFromUpperLocal(upperLocalNo);
    }

    @ApiOperation(value = "하위 지역 리스트 정보 조회 API")
    @GetMapping("/lower/info/list")
    public List<LowerLocalVO> lowerLocalSelectAll() {
        return localService.lowerLocalSelectAll();
    }

    @ApiOperation(value = "하위 지역 정보 삽입 API")
    @PostMapping("/lower/save")
    public Long lowerLocalInsert(
            @RequestBody @Valid LowerLocalInsertDTO lowerLocalInsertDTO) {
        return localService.lowerLocalInsert(lowerLocalInsertDTO);
    }

    @ApiOperation(value = "하위 지역 정보 수정 API")
    @PutMapping("/lower/update")
    public Long lowerLocalUpdate(
            @RequestBody @Valid LowerLocalUpdateDTO lowerLocalUpdateDTO) {
        return localService.lowerLocalUpdate(lowerLocalUpdateDTO);
    }

    @ApiOperation(value = "하위 지역 정보 삭제 API")
    @DeleteMapping("/lower/delete")
    public void lowerLocalDelete(
            @ApiParam(name = "하위 지역 번호", required = true, value = "lowerLocalNo", example = "1")
            @RequestParam("no") @Valid Long lowerLocalNo) {
        localService.lowerLocalDelete(lowerLocalNo);
    }
}
