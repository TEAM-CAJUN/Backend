package com.cajun.mds.service;

import com.cajun.jwt.JwtUtil;
import com.cajun.mds.domain.Member;
import com.cajun.mds.dto.MemberDto;
import com.cajun.mds.dto.MemberRegisterRequest;
import com.cajun.mds.dto.MemberUpdateRequest;
import com.cajun.mds.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                .orElseThrow(() -> new RuntimeException("가입되지 않은 회원입니다."));

        if (!bCryptPasswordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return JwtUtil.createToken(id, expireTimeMs, secretkey);
    }

    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    public boolean isIdDuplicate(String id) {
        return memberRepository.existsById(id);
    }

    public void delete(String id) {
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(selectMember -> {
            memberRepository.delete(selectMember);
        });
    }

    public MemberDto update(String id, MemberUpdateRequest updateRequest) {
        // 검색한 회원 정보를 가져옵니다.
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        // 업데이트할 필드가 있는지 확인하고 업데이트합니다.
        if (updateRequest.getPassword() != null) {
            existingMember.setPassword(bCryptPasswordEncoder.encode(updateRequest.getPassword()));
        }
        if (updateRequest.getBirth() != null) {
            existingMember.setBirth(updateRequest.getBirth());
        }

        if (updateRequest.getName() != null) {
            existingMember.setName(updateRequest.getName());
        }

        // 업데이트된 회원 정보를 저장합니다.
        Member updatedMember = memberRepository.save(existingMember);

        return MemberDto.fromEntity(updatedMember);
    }




}