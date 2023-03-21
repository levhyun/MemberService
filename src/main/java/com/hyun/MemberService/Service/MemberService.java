package com.hyun.MemberService.Service;

import com.hyun.MemberService.Dto.MemberDTO;
import com.hyun.MemberService.Entity.MemberEntity;
import com.hyun.MemberService.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 서비스 : 스프링이 관리해주는 객체이다. 즉,스프링 빈으로 등록 시킨다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void Register(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO); // MemberDto -> MemberEntity 변환
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(memberDTO.getEmail()); // Optional : null 방지
        if (byEmail.isPresent()) { // 조회 결과가 있을 때
            MemberEntity memberEntity = byEmail.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) { // memberEntity.getPassword()와 memberDTO.getPassword()을 비교하여 같을 때
                return MemberDTO.toMemberDTO(memberEntity); // 조회 성공한 유저 정보 반환
            } else {
                return null;
            }
        } else { // 조회 결과가 없을 때
            return null;
        }
    }

    public List<MemberDTO> findAllMembers() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findDetails(Long id) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
        if (memberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(memberEntity.get());
        } else {
            return null;
        }
    }

    public MemberDTO getMyInformations(String email) {
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(email);
        if (memberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(memberEntity.get());
        } else {
            return null;
        }
    }

    public void updateMemberInformations(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
