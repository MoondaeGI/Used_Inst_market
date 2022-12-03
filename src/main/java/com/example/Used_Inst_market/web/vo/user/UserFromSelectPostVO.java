package com.example.Used_Inst_market.web.vo.user;

import com.example.Used_Inst_market.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class UserFromSelectPostVO {
    private Long userNo;
    private String name;

    public UserFromSelectPostVO(User user) {
        this.userNo = user.getUserNo();
        this.name = user.getName();
    }

    public static UserFromSelectPostVO from(User user) {
        return new UserFromSelectPostVO(user);
    }
}
