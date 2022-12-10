package com.example.Used_Inst_market.web.controller.local;

import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.city.CityDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.city.CityInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.city.CitySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.city.CityUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.local.LocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.local.LocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.LocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.LocalUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.address.CityVO;
import com.example.Used_Inst_market.model.vo.address.LocalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/local")
@RestController
public class LocalController {
    private final LocalService localService;

    @GetMapping(value = "/info")
    public LocalVO localSelect(@RequestParam(name = "no") Long localNo) {
        LocalSelectRequestDTO localSelectRequestDTO = LocalSelectRequestDTO.builder()
                .localNo(localNo)
                .build();

        return localService.localSelect(localSelectRequestDTO);
    }

    @GetMapping(value = "/info/list")
    public List<LocalVO> localSelectAll() {
        return localService.localSelectAll();
    }

    @PostMapping(value = "/info")
    public Long localInsert(@RequestBody LocalInsertRequestDTO localInsertRequestDTO) {
        return localService.localInsert(localInsertRequestDTO);
    }

    @PutMapping(value = "/info")
    public Long localUpdate(@RequestBody LocalUpdateRequestDTO localUpdateRequestDTO) {
        return localService.localUpdate(localUpdateRequestDTO);
    }

    @DeleteMapping(value = "/info")
    public void localDelete(@RequestParam(name = "no") Long localNo) {
        LocalDeleteRequestDTO localDeleteRequestDTO = LocalDeleteRequestDTO.builder()
                .localNo(localNo)
                .build();

        localService.localDelete(localDeleteRequestDTO);
    }

    @GetMapping("/city/info")
    public CityVO citySelect(@RequestParam("no") Long cityNo) {
        CitySelectRequestDTO citySelectRequestDTO =
                CitySelectRequestDTO.builder()
                        .cityNo(cityNo)
                        .build();

        return localService.citySelect(citySelectRequestDTO);
    }

    @GetMapping("/city/info/list")
    public List<CityVO> citySelectAll() {
        return localService.citySelectAll();
    }

    @PostMapping("/city/info")
    public Long cityInsert(@RequestBody CityInsertRequestDTO cityInsertRequestDTO) {
        return localService.cityInsert(cityInsertRequestDTO);
    }

    @PutMapping("/city/info")
    public Long cityUpdate(@RequestBody CityUpdateRequestDTO cityUpdateRequestDTO) {
        return localService.cityUpdate(cityUpdateRequestDTO);
    }

    @DeleteMapping("/city/info")
    public void cityDelete(@RequestParam("no") Long cityNo) {
        CityDeleteRequestDTO cityDeleteRequestDTO =
                CityDeleteRequestDTO.builder()
                        .cityNo(cityNo)
                        .build();

        localService.cityDelete(cityDeleteRequestDTO);
    }
}
