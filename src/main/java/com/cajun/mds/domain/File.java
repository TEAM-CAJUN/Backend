package com.cajun.mds.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filePk;

    @NotNull
    private String filePath;

    @NotNull
    private int type;

    @Builder
    public File(String filePath, int type) {
        this.filePath = filePath;
        this.type = type;
    }
}
