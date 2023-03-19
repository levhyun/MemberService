package com.hyun.MemberService.Service;

import com.hyun.MemberService.Dto.MemberDto;
import com.hyun.MemberService.Entity.MemberEntity;
import com.hyun.MemberService.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // 서비스 : 스프링이 관리해주는 객체이다. 즉,스프링 빈으로 등록 시킨다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void Register(MemberDto memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO); // MemberDto -> MemberEntity 변환
        memberRepository.save(memberEntity);
    }

    public MemberDto login(MemberDto memberDTO) {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(memberDTO.getEmail()); // Optional : null 방지
        if (byEmail.isPresent()) { // 조회 결과가 있을 때
            MemberEntity memberEntity = byEmail.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) { // memberEntity.getPassword()와 memberDTO.getPassword()을 비교하여 같을 때
                MemberDto memberDto = MemberDto.toMemberDTO(memberEntity); // MemberEntity -> MemberDto 변환
                return memberDto; // 조회 성공한 유저 정보 반환
            } else {
                return null;
            }
        } else { // 조회 결과가 없을 때
            return null;
        }
    }
}
