package com.cajun.mds.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Region {
    @Id
    private Long regionPk;

    @NotNull
    private String gu;

    @NotNull
    private String dong;
}
