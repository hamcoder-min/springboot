package com.springboot.shoppy_fullstack_app.service;

import com.springboot.shoppy_fullstack_app.dto.Member;
import com.springboot.shoppy_fullstack_app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //memberServiceImpl
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int signup(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public boolean idCheck(String id) {
        boolean result = true;
        long count = memberRepository.findById(id);
        System.out.println("count -> " + count);
        if(count == 0) result = false;
        return result;
    }

    @Override
    public boolean login(String id, String pwd) {
        boolean result = true;
        long count = memberRepository.checkMember(id, pwd);
        System.out.println("count --> " + count);
        if(count == 0) result = false;
        return result;
    }
}
