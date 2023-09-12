package com.cajun.mds.service;

import com.cajun.jwt.JwtUtil;
import com.cajun.mds.domain.Member;
import com.cajun.mds.dto.MemberDto;
import com.cajun.mds.dto.MemberRegisterRequest;
import com.cajun.mds.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${jwt.secret}")
    private String secretkey;
    private final long expireTimeMs = 1000 * 60 * 60 * 24 * 7; // 토큰 7일

    public MemberDto register(MemberRegisterRequest request) {
        memberRepository.findById(request.getId())
                .ifPresent(member -> {
                    throw new RuntimeException();
                });

        Member saveMember = memberRepository.save(request.toEntity(bCryptPasswordEncoder.encode(request.getPassword())));
        return MemberDto.fromEntity(saveMember);
    }

    public String login(String id, String password) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("가입되지 않은 사원입니다."));

        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return JwtUtil.createToken(id, expireTimeMs, secretkey);
    }
}