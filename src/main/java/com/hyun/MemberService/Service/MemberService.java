package com.hyun.MemberService.Service;

import com.hyun.MemberService.Dto.MemberDto;
import com.hyun.MemberService.Entity.MemberEntity;
import com.hyun.MemberService.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 서비스 : 스프링이 관리해주는 객체이다. 즉,스프링 빈으로 등록 시킨다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void Register(MemberDto memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDto login(MemberDto memberDTO) {

    }
}
