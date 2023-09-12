package com.cajun.mds.controller;


import com.cajun.mds.dto.*;
import com.cajun.mds.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberRegisterResponse> register(@RequestBody MemberRegisterRequest memberRegisterRequest) {
        try {
            MemberDto memberDto = memberService.register(memberRegisterRequest);
            MemberRegisterResponse response = new MemberRegisterResponse(memberDto.getId());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        try {
            String token = memberService.login(memberLoginRequest.getId(), memberLoginRequest.getPassword());
            return new ResponseEntity<>(new MemberLoginResponse(token), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}