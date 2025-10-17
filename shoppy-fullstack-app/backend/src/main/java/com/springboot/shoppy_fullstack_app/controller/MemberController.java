package com.springboot.shoppy_fullstack_app.controller;

import com.springboot.shoppy_fullstack_app.dto.Member;
import com.springboot.shoppy_fullstack_app.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = {"http://localhost:3000"})
public class MemberController {

    //서비스 객체 가져오기
    private final MemberService memberService;

    //생성자
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService; //컨테이너에 생성된 서비스 객체
    }


    @PostMapping("/login")
    public boolean login(@RequestBody Member member) {
        boolean result = false;
        boolean rows = memberService.login(member.getId(), member.getPwd());

        return rows;
    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody Member member) {
        boolean result = true;
        //서비스 호출
        int rows = memberService.signup(member);
        if(rows == 1) result = true;
        return result;
    }

    @PostMapping("/idcheck")
    public String idcheck(@RequestBody Member member) {
        boolean result = memberService.idCheck(member.getId());

        String msg = "";
        if(result) msg = "이미 사용중인 아이디 입니다.";
        else msg = "사용 가능한 아이디 입니다.";

        return msg;
    }
}
