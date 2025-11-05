package com.springboot.shoppy_fullstack_app.repository;

import com.springboot.shoppy_fullstack_app.dto.MemberDto;

import java.util.Optional;

public interface MemberRepository {
    public int save(MemberDto member);
    public long findById(String id);
    public String login(String id);
    Optional<MemberDto> findByMember(String id);
}
