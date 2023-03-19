package com.hyun.MemberService.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto { // 회원 정보에 필요한  내용을 필드로 정의
    private Long id;
    private String email;
    private String password;
    private String name;
}
