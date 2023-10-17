package com.cajun.mds.domain;

import com.cajun.mds.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatPk;

    @NotNull
    @Size(max = 50)
    private String direction;

    @CreationTimestamp
    private LocalDateTime date;

    @NotNull
    @Size(max = 500)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @Builder
    public Chat(String direction, String message) {
        this.direction = direction;
        this.message = message;
    }

    public void setMember(Member member) {
        this.member = member;
        if (!member.getChatList().contains(this)) {
            member.getChatList().add(this);
        }
    }
}