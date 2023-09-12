package com.cajun.mds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequest {
    private String id;
    private String password;
}