package com.cajun.mds.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
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
    @Setter
    @Size(max = 5000)
    private String password;

    @NotNull
    @Setter
    @Size(max = 10)
    private String name;

    @NotNull
    @Setter
    @Size(max = 10)
    private String birth;

    private String agentNumber;

    private int signWith;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy="member", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Chat> chatList = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "region_pk")
//    private Region region;
    @Builder
    public Member(String id, String password, String name, String birth, String agentNumber, int signWith) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.agentNumber = agentNumber;
        this.signWith = signWith;
    }
}
