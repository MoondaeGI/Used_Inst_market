package com.example.Used_Inst_market.model.vo.user;

import com.example.Used_Inst_market.model.domain.user.User;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class UserVO {
    @ApiParam(name = "유저 번호", required = true, value = "userNo", example = "1")
    private Long userNo;
    @ApiParam(name = "유저 이름", required = true, value = "name", example = "example")
    private String name;

    public UserVO(User user) {
        this.userNo = user.getUserNo();
        this.name = user.getName();
    }
}
