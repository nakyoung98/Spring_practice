package com.nakyoung.hellospring;

//Configuration 문서

import com.nakyoung.hellospring.repository.JpaMemberRepository;
import com.nakyoung.hellospring.repository.MemberRepository;
import com.nakyoung.hellospring.repository.MemoryMemberRepository;
import com.nakyoung.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    //Spring bean에 등록하라는 뜻임을 알아서 인지함
    //@Component와 Autowired 행동을 수행함
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }


    //이렇게 bean으로 처리해놓으면
    //memberRepository()에서 실제 구현 클래스가 달라지게되더라도
    //return new MemoryMemberRepository(); 부분만 클래스 생성자를 바꿔주면 되기 때문에 몹시 편하다
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
