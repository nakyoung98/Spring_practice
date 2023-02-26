package com.nakyoung.hellospring.service;

import com.nakyoung.hellospring.domain.Member;
import com.nakyoung.hellospring.repository.JpaMemberRepository;
import com.nakyoung.hellospring.repository.MemberRepository;
import com.nakyoung.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        ((MemoryMemberRepository) memberRepository).clearStore();
    }


    //Test 코드는 한글로도 많이 적음
    @Test
    void 회원가입() {
        //given (주어진 것)
        Member member = new Member();
        member.setName("나경");

        //when (이것을 실행했을 때)
        Long saveId = memberService.join(member);

        //then (이 결과가 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("나경");

            //shift+f6
        Member member2 = new Member();
        member2.setName("나경");

        //when
        memberService.join(member1);

        //단순한 예외처리
//        try {
//            memberService.join(member2); //예외가 기대됨
//            fail("예외가 발생해야함");
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("");
//        }

        //편리한 예외처리
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
            //오류
        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        //then
    }

    @Test
    void 멤버찾기() {
    }

    @Test
    void findOne() {
    }
}