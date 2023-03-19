package com.hyun.MemberService.Repository;

import com.hyun.MemberService.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> { // < 엔티티, pk 타입 >

    Optional<MemberEntity> findByEmail(String email); // Optional : null 방지
}
