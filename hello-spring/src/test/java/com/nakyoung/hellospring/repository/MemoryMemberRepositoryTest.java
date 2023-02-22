package com.nakyoung.hellospring.repository;

import com.nakyoung.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

//다른데에서 가져다 쓸게 아니니 public은 삭제해도 됨
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test가 하나 끝나면 repository를 지워줘야함
    //test 함수들은 실행순서가 보장되지 않기 때문에, 데이터가 저장소에 섞일 가능성 농후
    //실행이 끝날때마다 실행되는 콜백 메소드
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    //Test annotation을 넣어주면 됨
    @Test
    public void save(){
        //우리가 실제로 기대하는 데이터
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //repository에서 찾아낸 결과 값
        Member result = repository.findById(member.getId()).get();//optional에서는 get으로 값을 꺼낼 수 있음

        //둘이 같은지 체크 static으로 해당 라이브러리를 추가해놓으면 그 이후부터는 별도의 Assertion 입력 없이 함수만가져올 수 있음
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift+F6으로 편하게 변경 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);

        result = repository.findByName("spring2").get();
        assertThat(member2).isEqualTo(result);


        List<Member> members = new ArrayList<>();
        Random random = new Random();
        int testcase = 200;

        for(int i = 0; i<testcase; i++){
            Member tempMember = new Member();
            tempMember.setName(String.valueOf(random.nextInt()));
            members.add(tempMember);
            repository.save(tempMember);
            //오류 확인을 위해
            //members.add(new Member());
        }

        for(int i = 0; i<testcase;i++){
            assertThat(members.get(i)).isEqualTo(repository.findByName(members.get(i).getName()).get());
        }
    }

    @Test
    public void findAll(){
        List<Member> members = new ArrayList<>();
        Random random = new Random();
        int testcase = 200;

        for(int i = 0; i<testcase; i++){
            Member tempMember = new Member();
            tempMember.setName(String.valueOf(random.nextInt()));
            members.add(tempMember);
            repository.save(tempMember);
            //오류 확인을 위해
            //members.add(new Member());
            //repository.add(new Member());
        }

        assertThat(repository.findAll()).isEqualTo(members);
    }
}
