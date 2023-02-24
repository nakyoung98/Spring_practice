package com.nakyoung.hellospring.controller;

import com.nakyoung.hellospring.domain.Member;
import com.nakyoung.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Controller annotation이 달려있으면
//실행시점에 해당 클래스의 객체가 자동으로 생성이 되고
//스프링에서 이를 관리한다.
@Controller
public class MemberController {

    //MemverService 객체는 하나만 만들어놓고 여기저기에서 공유해서 쓴다
    private final MemberService memberService;

    //생성자 호출 시 Autowired가 있으면 스프링이 가지고 있는 memberService 객체를 가져다가 넣어준다
    //그런데 빈으로 등록되어있지 않으면 실행시 객체가 생서오디지 않기에 넣어놓을 수가 없음
    //MemberService는 아무 Annotation이 없으니 그냥 순수 자바코드
    //이걸 bin에 넣으려면 @Service Annotation을 해당 Service 클래스 위에 달면 됨
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
