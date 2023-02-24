package com.nakyoung.hellospring.repository;

import com.nakyoung.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

//Repository라고 bin에 담아두는 것
public class MemoryMemberRepository implements MemberRepository{

    //save시 저장할 변수
    //실제 활용시 문제: 공유되는 부분의 경우 Map을 사용하면 Concurrency(동시성) 오류가 있기 떄문에
    //               ConcurrentHashMap을 써야함
    private static Map<Long, Member> store = new HashMap<>();

    //Key값을 생성해주는 변수
    //실제 활용시 문제: 공유되는 부분의 경우 그냥 Long을 사용하면 Concurrency(동시성) 오류가 있기 떄문에
    //               AtomicLong 써야함
    private static AtomicLong sequence = new AtomicLong(0L);


    /**
     * Member을 save하는 함수
     * member객체가 인자로 넘어오는 시점에 name은 이미 정해져있다
     * id를 부여하여 저장
     * return Member
     * **/
    @Override
    public Member save(Member member) {
        //1을 증가시켜 ID저장
        member.setId(sequence.addAndGet(1L));
        store.put(member.getId(),member);
        return member;
    }

    /**
     * store에서 id로 유저 검색하여 반환
     * null 반환 가능성이 있으므로 optional으로 감싸 반환
     * **/
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //추후 클라이언트에서 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나찾으면 반환 아니면 optional으로 감싸서 반환하는 메소드
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
