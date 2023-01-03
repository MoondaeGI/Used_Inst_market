package com.example.Used_Inst_market.model.vo.user;

import com.example.Used_Inst_market.model.domain.user.User;
import io.swagger.annotations.ApiParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
public class UserVO implements Serializable {
    @ApiParam(name = "유저 번호", required = true, value = "userNo", example = "1")
    private Long userNo;
    @ApiParam(name = "유저 이름", required = true, value = "name", example = "example")
    private String name;
    @ApiParam(name = "유저 이메일", required = true, value = "email", example = "test1234@test.com")
    private final String email;
    @ApiParam(name = "유저 이미지", required = true, value = "picture", example = "test.jpg")
    private final String picture;

    public UserVO(User user) {
        this.userNo = user.getUserNo();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

    public static UserVO from(User user) {
        return new UserVO(user);
    }
}
