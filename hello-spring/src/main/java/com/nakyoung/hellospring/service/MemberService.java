package com.nakyoung.hellospring.service;

import com.nakyoung.hellospring.domain.Member;
import com.nakyoung.hellospring.repository.MemberRepository;
import com.nakyoung.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// service class의 함수 네이밍은 비즈니스적으로 짓는 것이 좋음
// Test를 쉽게 만드는 법 -> 클래스명에 커서대로 ctrl+shift+t(est) -> test생성으로 넘어감

//Service 어노테이션이 궁금하면 MemberController 파일로 이동
public class MemberService {

    private final MemberRepository memberRepository;

    //Autowired가 궁금하면 MemberController의 Autowired를 확인
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * **/

    @Transactional
    public Long join(Member member){
        //중복 이름 회원 X

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    //만약 함수로 뽑는게 좋다면, 만들어놓은 코드를 긁은다음 ctrl+alt+m하면 되고, 만약 뭘 처리할지 아직 못결정했으면 ctrl+alt+shift+T 하면 할 행동 선택 가능
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                //optional로 감쌌기 때문에 ifPresent로 null 여부 체크, null이 아닐 경우 예외처리하는 람다 처리가능
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
