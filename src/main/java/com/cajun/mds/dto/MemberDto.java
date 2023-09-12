package com.cajun.mds.dto;

import com.cajun.mds.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberDto {
    private String id;
    private String password;
    private String name;
    private String birth;
    private String agentNumber;
    private int signWith;

    public static MemberDto fromEntity(Member member)  {
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .password(member.getPassword())
                .name(member.getName())
                .birth(member.getBirth())
                .agentNumber(member.getAgentNumber())
                .signWith(member.getSignWith())
                .build();
        return memberDto;
    }
}