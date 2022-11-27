package com.example.Used_Inst_market.web.controller.address;

import com.example.Used_Inst_market.service.address.LocalService;
import com.example.Used_Inst_market.web.dto.address.local.LocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.LocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.LocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.LocalUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.address.LocalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/local")
@RestController
public class LocalController {
    private final LocalService localService;

    @GetMapping(value = "/info")
    public LocalVO select(@RequestParam(name = "no") Long localNo) {
        LocalSelectRequestDTO localSelectRequestDTO = LocalSelectRequestDTO.builder()
                .localNo(localNo)
                .build();

        return localService.select(localSelectRequestDTO);
    }

    @GetMapping(value = "/info/list")
    public List<LocalVO> selectAll() {
        return localService.selectAll();
    }

    @PostMapping(value = "/info")
    public Long insert(@RequestBody LocalInsertRequestDTO localInsertRequestDTO) {
        return localService.insert(localInsertRequestDTO);
    }

    @PutMapping(value = "/info")
    public Long update(@RequestBody LocalUpdateRequestDTO localUpdateRequestDTO) {
        return localService.update(localUpdateRequestDTO);
    }

    @DeleteMapping(value = "/info")
    public void delete(@RequestParam(name = "no") Long localNo) {
        LocalDeleteRequestDTO localDeleteRequestDTO = LocalDeleteRequestDTO.builder()
                .localNo(localNo)
                .build();

        localService.delete(localDeleteRequestDTO);
    }
}
