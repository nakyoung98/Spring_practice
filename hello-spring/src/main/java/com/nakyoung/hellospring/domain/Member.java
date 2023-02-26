package com.nakyoung.hellospring.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//ORM 구조의 jpa를 사용하려면 (Object Relational Mapping)
//데이터 베이스에 등록할 객체를 Entity화 시켜줘야함 (= @Entity)
@Entity
public class Member {

    //pk를 정해줘야함 @Id, DB가 알아서 값 생성
    @Id @GeneratedValue
    private Long id; //System이 정하는 id

    //table의 username 컬럼에 대응됨
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
