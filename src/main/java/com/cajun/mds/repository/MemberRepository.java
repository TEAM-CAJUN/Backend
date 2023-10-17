package com.cajun.mds.repository;

import com.cajun.mds.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
    boolean existsById(String id);
}
