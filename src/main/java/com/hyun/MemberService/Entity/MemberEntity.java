package com.hyun.MemberService.Entity;

import com.hyun.MemberService.Dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Members") // 테이블 등록
public class MemberEntity { // 테이블을 정의한다.
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) // unique 제약 조건
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    public static MemberEntity toMemberEntity(MemberDTO memberDto) { // MemberDto -> MemberEntity 변환
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setEmail(memberDto.getEmail());
        memberEntity.setPassword(memberDto.getPassword());
        memberEntity.setName(memberDto.getName());
        return memberEntity;
    }
}
