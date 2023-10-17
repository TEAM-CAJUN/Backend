package com.cajun.mds.controller;


import com.cajun.mds.domain.Member;
import com.cajun.mds.dto.*;
import com.cajun.mds.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Optional<Member> memberDto = memberService.getMemberById(id);
        if (memberDto.isPresent()) {
            return ResponseEntity.ok(memberDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/is-exist/{id}")
    public ResponseEntity<Boolean> isIdExist(@PathVariable String id) {
        boolean result = memberService.isIdDuplicate(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable String id, @RequestBody MemberUpdateRequest updateRequest) {
        MemberDto updatedMember = memberService.update(id, updateRequest);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }
}