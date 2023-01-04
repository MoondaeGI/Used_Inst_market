package com.example.Used_Inst_market.model.vo.local;

import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelect;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class LocalSelectVO {
    @ApiParam(name = "상위 지역", readOnly = true,
            value = "upperLocal", example = "{upperLocalNo: '1', name: 'example'}")
    private UpperLocalVO upperLocal;
    @ApiParam(name = "하위 지역", readOnly = true,
            value = "lowerLocal", example = "{lowerLocalNo: '1', name: 'example'}")
    private LowerLocalVO lowerLocal;

    private LocalSelectVO(LocalSelect localSelect) {
        this.upperLocal = UpperLocalVO.from(localSelect.getUpperLocal());
        this.lowerLocal = LowerLocalVO.from(localSelect.getLowerLocal());
    }

    public static LocalSelectVO from(LocalSelect localSelect) {
        return new LocalSelectVO(localSelect);
    }
}
