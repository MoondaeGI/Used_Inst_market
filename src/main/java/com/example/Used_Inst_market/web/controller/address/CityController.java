package com.example.Used_Inst_market.web.controller.address;

import com.example.Used_Inst_market.service.address.CityService;
import com.example.Used_Inst_market.web.dto.address.city.CityDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CitySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.address.CityVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("city")
@RestController
public class CityController {
    private final CityService cityService;

    @GetMapping("/select")
    public CityVO select(@RequestParam("cityNo") Long cityNo) {
        CitySelectRequestDTO citySelectRequestDTO =
                CitySelectRequestDTO.builder()
                        .cityNo(cityNo)
                        .build();

        return cityService.select(citySelectRequestDTO);
    }

    @GetMapping("/selectAll")
    public List<CityVO> selectAll() {
        return cityService.selectAll();
    }

    @PostMapping("/insert")
    public Long insert(@RequestBody CityInsertRequestDTO cityInsertRequestDTO) {
        return cityService.insert(cityInsertRequestDTO);
    }

    @PutMapping("/update")
    public Long update(@RequestBody CityUpdateRequestDTO cityUpdateRequestDTO) {
        return cityService.update(cityUpdateRequestDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("cityNo") Long cityNo) {
        CityDeleteRequestDTO cityDeleteRequestDTO =
                CityDeleteRequestDTO.builder()
                        .cityNo(cityNo)
                        .build();

        cityService.delete(cityDeleteRequestDTO);
    }
}
