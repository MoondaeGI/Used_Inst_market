package com.example.Used_Inst_market.web.controller.brand;

import com.example.Used_Inst_market.service.brand.BrandService;
import com.example.Used_Inst_market.web.dto.brand.BrandDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.BrandInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.BrandSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.BrandUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.brand.BrandVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("brand")
@RestController
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/info")
    public BrandVO select(@RequestParam(name = "no") Long brandNo) {
        BrandSelectRequestDTO brandSelectRequestDTO =
                BrandSelectRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        return brandService.select(brandSelectRequestDTO);
    }

    @GetMapping("/info/list")
    public List<BrandVO> selectAll() {
        return brandService.selectAll();
    }

    @PostMapping("/info")
    public Long insert(@RequestBody BrandInsertRequestDTO brandInsertRequestDTO) {
        return brandService.insert(brandInsertRequestDTO);
    }

    @PutMapping("/info")
    public Long update(@RequestBody BrandUpdateRequestDTO brandUpdateRequestDTO) {
        return brandService.update(brandUpdateRequestDTO);
    }

    @DeleteMapping("/info")
    public void delete(@RequestParam(name = "no") Long brandNo) {
        BrandDeleteRequestDTO brandDeleteRequestDTO =
                BrandDeleteRequestDTO.builder()
                        .brandNo(brandNo)
                        .build();

        brandService.delete(brandDeleteRequestDTO);
    }
}
