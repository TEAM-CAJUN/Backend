package com.cajun.mds.controller;


import com.cajun.mds.domain.Member;
import com.cajun.mds.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("member")
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @PostMapping("member")
    public Member register() {
        final Member member = new Member("asd@naver.com", "1234", "test", new Date(1997, 8, 04), "111122223333", 0, "123");
        return memberRepository.save(member);
    }

    @DeleteMapping("member")
    public void remove(long key) {
        final Member member = memberRepository.getReferenceById(key);
        memberRepository.delete(member);
    }

    @PatchMapping("member")
    public void update(Member member) {
        
    }


}
