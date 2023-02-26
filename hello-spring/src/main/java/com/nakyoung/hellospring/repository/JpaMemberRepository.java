package com.nakyoung.hellospring.repository;

import com.nakyoung.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{

    //jpa는 entityManager로 동작함
    //Spring이 실행시 EntityManager 객체는 알아서 만들어주기 떄문에, 생성자 주입으로 삽입만 하면 됨
    private final EntityManager em;


    //Constructor
    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    //Overrided Method
    @Override
    public Member save(Member member) {
        //영구저장
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //조회할 클래스와 id를 find에 입력하면 됨
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        //아무거나 첫번째꺼 찾으면 됨
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //select *가 아니라 select m 인 이유
        //Member m 객체 자체를 select 하는 것
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
