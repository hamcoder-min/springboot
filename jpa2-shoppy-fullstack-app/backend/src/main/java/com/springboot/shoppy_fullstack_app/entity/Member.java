package com.springboot.shoppy_fullstack_app.entity;

import com.springboot.shoppy_fullstack_app.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Setter
@Getter //@Data를 주면 안됨!! 그러면 Dto 처럼 데이터만 저장되고 DB연동이 안됨
public class Member {
    @Id
    @Column(name="id", length = 50)
    private String id;
    private String pwd;

    @Column(name="name")
    private String name;
    private String phone;
    private String email;
    private String role;
    private LocalDate mdate;

    //Member(1) : (N) CartItem
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItemList = new ArrayList<>();

    //✨ 생성자를 반드시 정의!!
    public Member() {}
    public Member(MemberDto memberDto) {
        this.id = memberDto.getId();
        this.pwd = memberDto.getPwd();
        this.name = memberDto.getName();
        this.phone = memberDto.getPhone();
        this.email = memberDto.getEmail();
        this.mdate = LocalDate.now();
        this.role = memberDto.getRole();
    }
}
