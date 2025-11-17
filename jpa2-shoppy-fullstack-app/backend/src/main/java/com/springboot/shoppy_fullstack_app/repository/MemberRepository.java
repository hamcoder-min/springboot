package com.springboot.shoppy_fullstack_app.repository;

import com.springboot.shoppy_fullstack_app.dto.MemberDto;
import com.springboot.shoppy_fullstack_app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//interface를 구현하는 클래스를 생성하는 작업은 Spring Data JPA --> 서버부팅시 컨테이너에 생성 후 로딩
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member save(Member member);   //✨✨ 생략 ⭕ : 상속받은 부모 인터페이스에 save 메소드가 존재
    Long countById(@Param("id") String id); //네이밍 규칙 적용 : 간단한 SQL 생성 후 실행결과 출력
    Optional<Member>findById(@Param("id") String id);   //로그인 인증
}
