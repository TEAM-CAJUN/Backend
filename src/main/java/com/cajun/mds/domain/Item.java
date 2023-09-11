package com.cajun.mds.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemPk;

    @NotNull
    private String addressDetail;

    @NotNull
    private int price; // 전세가

    @NotNull
    private double totalSquare; // 공급면적

    @NotNull
    private double unitSquare; // 단일면적

    @ColumnDefault("false")
    private boolean isDeal;

    @ColumnDefault("0")
    private int isInsurance;

    private String description;

    @ColumnDefault("0")
    private int isLoans;

    @ColumnDefault("0")
    private int isPaper;

    @ColumnDefault("0")
    private int isBpaper;

    @ColumnDefault("0")
    private boolean isPhoto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="region_code_fk", referencedColumnName="region_code"),
            @JoinColumn(name="dong_code_fk", referencedColumnName="dong_code")
    })
    private Region region;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_pk")
//    private Member member;

    @Builder
    public Item(Region region, int price, String addressDetail, double totalSquare, double unitSquare, boolean isDeal, int isInsurance, String description, int isLoans, int isPaper, int isBpaper, boolean isPhoto) {
        this.region = region;
        this.price = price;
        this.addressDetail = addressDetail;
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
//    @Builder
//    public Item(Member member, int price,String addressDetail, double totalSquare, double unitSquare, boolean isDeal, int isInsurance, String description, int isLoans, int isPaper, int isBpaper, boolean isPhoto) {
//        this.member = member;
//        this.price = price;
//        this.addressDetail = addressDetail;
//        this.totalSquare = totalSquare;
//        this.unitSquare = unitSquare;
//        this.isDeal = isDeal;
//        this.isInsurance = isInsurance;
//        this.description = description;
//        this.isLoans = isLoans;
//        this.isPaper = isPaper;
//        this.isBpaper = isBpaper;
//        this.isPhoto = isPhoto;
//    }
}
