package com.example.Used_Inst_market.web.controller.address;

import com.example.Used_Inst_market.service.address.LocalService;
import com.example.Used_Inst_market.web.dto.address.local.LocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.LocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.LocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.LocalUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.address.LocalInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/local")
@RestController
public class LocalController {
    private final LocalService localService;

    @GetMapping(value = "/select")
    public LocalInfoVO select(@RequestParam("localNo") Long localNo) {
        LocalSelectRequestDTO localSelectRequestDTO = LocalSelectRequestDTO.builder()
                .localNo(localNo)
                .build();

        return localService.select(localSelectRequestDTO);
    }

    @GetMapping(value = "selectAll")
    public List<LocalInfoVO> selectAll() {
        return localService.selectAll();
    }

    @PostMapping(value = "/insert")
    public Long insert(@RequestBody LocalInsertRequestDTO localInsertRequestDTO) {
        return localService.insert(localInsertRequestDTO);
    }

    @PutMapping(value = "/update")
    public Long update(@RequestBody LocalUpdateRequestDTO localUpdateRequestDTO) {
        return localService.update(localUpdateRequestDTO);
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam("localNo") Long localNo) {
        LocalDeleteRequestDTO localDeleteRequestDTO = LocalDeleteRequestDTO.builder()
                .localNo(localNo)
                .build();

        localService.delete(localDeleteRequestDTO);
    }
}
