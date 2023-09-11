package com.cajun.mds.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@IdClass(RegionKey.class)
public class Region {
    @Id
    @Column(name = "region_code")
    private Integer regionCode;

    @Id
    @Column(name = "dong_code")
    private Integer dongCode;

    @NotNull
    private String gu;

    @NotNull
    private String dong;

    //test 용 삭제 예정
    public Region(Integer regionCode, Integer dongCode, String gu, String dong) {
        this.regionCode = regionCode;
        this.dongCode = dongCode;
        this.gu = gu;
        this.dong = dong;
    }
}
