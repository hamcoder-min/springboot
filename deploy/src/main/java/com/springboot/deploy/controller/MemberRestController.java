package com.springboot.deploy.controller;

import com.springboot.deploy.dto.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {
    @PostMapping("/restLogin")
    public void restLogin(@RequestBody Member member) {
        boolean result = false;
        if(member.getId().equals("test") && member.getPass().equals("123")) result = true;

    }
}
