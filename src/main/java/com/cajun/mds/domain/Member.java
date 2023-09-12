package com.cajun.mds.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberPk;

    @NotNull
    @Size(max = 50)
    private String id;

    @NotNull
    @Size(max = 50)
    private String password;

    @NotNull
    @Size(max = 10)
    private String name;

    @NotNull
    private Date birth;

    private String agentNumber;

    private int signWith;

    private String token;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chatList = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "region_pk")
//    private Region region;
    @Builder
    public Member(String id, String password, String name, Date birth, String agentNumber, int signWith, String token) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.agentNumber = agentNumber;
        this.signWith = signWith;
        this.token = token;
    }
}
