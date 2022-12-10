package com.example.Used_Inst_market.model.domain.user;

import com.example.Used_Inst_market.model.domain.address.Address;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class User extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    private Long userNo;

    @OneToOne(mappedBy = "user",
            fetch = FetchType.LAZY, orphanRemoval = true)
    public Address address;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.GUEST;
    }

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Post> posts = new ArrayList<Post>();

    public String getRoleKey() {
        return this.role.getKey();
    }
}
