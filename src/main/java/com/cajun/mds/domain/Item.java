package com.cajun.mds.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemPk;

    @NotNull
    private int price; // 전세가

    @NotNull
    private double totalSquare; // 공급면적

    @NotNull
    private double unitSquare; // 단일면적

    @Column(columnDefinition = "boolean default false")
    private boolean isDeal;

    @Column(columnDefinition = "default 0")
    private int isInsurance;

    private String description;

    @Column(columnDefinition = "default 0")
    private int isLoans;

    @Column(columnDefinition = "default 0")
    private int isPaper;

    @Column(columnDefinition = "default 0")
    private int isBpaper;

    @Column(columnDefinition = "default 0")
    private boolean isPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;
    @Builder
    public Item(int price, double totalSquare, double unitSquare, boolean isDeal, int isInsurance, String description, int isLoans, int isPaper, int isBpaper, boolean isPhoto) {
        this.price = price;
        this.totalSquare = totalSquare;
        this.unitSquare = unitSquare;
        this.isDeal = isDeal;
        this.isInsurance = isInsurance;
        this.description = description;
        this.isLoans = isLoans;
        this.isPaper = isPaper;
        this.isBpaper = isBpaper;
        this.isPhoto = isPhoto;
    }
}
