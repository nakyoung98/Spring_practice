package com.nakyoung.hellospring.repository;

import com.nakyoung.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional?
    // java8부터 들어온 기능
    // return된 값이 Null 일때, Null을 그대로 처리하는 대신 Optional로 감싸서 처리하는 경우가 많음
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
