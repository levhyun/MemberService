package com.hyun.MemberService.Dto;

import com.hyun.MemberService.Entity.MemberEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto { // 회원 정보에 필요한 내용을 필드로 정의
    private Long id;
    private String email;
    private String password;
    private String name;

    public static MemberDto toMemberDTO(MemberEntity memberEntity) { // MemberEntity -> MemberDto 변환
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setPassword(memberEntity.getPassword());
        memberDto.setName(memberEntity.getName());
        return memberDto;
    }
}
