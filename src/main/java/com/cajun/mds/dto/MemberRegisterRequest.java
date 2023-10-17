package com.cajun.mds.dto;


import com.cajun.mds.domain.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class MemberRegisterRequest {
    private String id;
    private String password;
    private String name;
    private String birth;
    private String agentNumber;
    private int signWith;

    public Member toEntity(String password) {
        return Member.builder()
                .id(this.id)
                .password(password)
                .name(this.name)
                .birth(this.birth)
                .agentNumber(this.agentNumber)
                .signWith(this.signWith)
                .build();
    }
}