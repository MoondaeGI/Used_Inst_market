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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/local")
@RestController
public class LocalController {
    private final LocalService localService;

    @GetMapping(value = "/upper/info")
    public UpperLocalVO upperLocalSelect(
            @RequestParam(name = "no") Long upperLocalNo) {
        UpperLocalSelectRequestDTO upperLocalSelectRequestDTO =
                UpperLocalSelectRequestDTO.builder()
                        .upperLocalNo(upperLocalNo)
                        .build();

        return localService.upperLocalSelect(upperLocalSelectRequestDTO);
    }

    @GetMapping(value = "/upper/info/list")
    public List<UpperLocalVO> upperLocalSelectAll() {
        return localService.upperLocalSelectAll();
    }

    @PostMapping(value = "/info")
    public Long upperLocalInsert(
            @RequestBody UpperLocalInsertRequestDTO upperLocalInsertRequestDTO) {
        return localService.upperLocalInsert(upperLocalInsertRequestDTO);
    }

    @PutMapping(value = "/upper/info")
    public Long upperLocalUpdate(
            @RequestBody UpperLocalUpdateRequestDTO upperLocalUpdateRequestDTO) {
        return localService.upperLocalUpdate(upperLocalUpdateRequestDTO);
    }

    @DeleteMapping(value = "/upper/info")
    public void upperLocalDelete(@RequestParam(name = "no") Long upperLocalNo) {
        UpperLocalDeleteRequestDTO upperLocalDeleteRequestDTO =
                UpperLocalDeleteRequestDTO.builder()
                        .upperLocalNo(upperLocalNo)
                        .build();

        localService.upperLocalDelete(upperLocalDeleteRequestDTO);
    }

    @GetMapping("/lower/info")
    public LowerLocalVO lowerLocalSelect(@RequestParam("no") Long lowerLocalNo) {
        LowerLocalSelectRequestDTO lowerLocalSelectRequestDTO =
                LowerLocalSelectRequestDTO.builder()
                        .lowerLocalNo(lowerLocalNo)
                        .build();

        return localService.lowerLocalSelect(lowerLocalSelectRequestDTO);
    }

    @GetMapping("/lower/info/list")
    public List<LowerLocalVO> lowerLocalSelectAll() {
        return localService.lowerLocalSelectAll();
    }

    @PostMapping("/lower/info")
    public Long lowerLocalInsert(
            @RequestBody LowerLocalInsertRequestDTO lowerLocalInsertRequestDTO) {
        return localService.lowerLocalInsert(lowerLocalInsertRequestDTO);
    }

    @PutMapping("/lower/info")
    public Long lowerLocalUpdate(
            @RequestBody LowerLocalUpdateRequestDTO lowerLocalUpdateRequestDTO) {
        return localService.lowerLocalUpdate(lowerLocalUpdateRequestDTO);
    }

    @DeleteMapping("/lower/info")
    public void lowerLocalDelete(@RequestParam("no") Long lowerLocalNo) {
        LowerLocalDeleteRequestDTO lowerLocalDeleteRequestDTO =
                LowerLocalDeleteRequestDTO.builder()
                        .lowerLocalNo(lowerLocalNo)
                        .build();

        localService.lowerLocalDelete(lowerLocalDeleteRequestDTO);
    }
}
