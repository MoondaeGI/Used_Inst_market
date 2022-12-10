package com.example.Used_Inst_market.model.vo.user;

import com.example.Used_Inst_market.model.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class UserSelectPostReqVO {
    private Long userNo;
    private String name;

    public UserSelectPostReqVO(User user) {
        this.userNo = user.getUserNo();
        this.name = user.getName();
    }

    public static UserSelectPostReqVO from(User user) {
        return new UserSelectPostReqVO(user);
    }
}
