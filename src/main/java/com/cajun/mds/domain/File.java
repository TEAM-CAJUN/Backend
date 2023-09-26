package com.cajun.mds.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filePk;

    @NotNull
    private String filePath;

    @NotNull
    private String fileName;

    @NotNull
    private String originalFileName;

    @NotNull
    private int type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @Builder
    public File(Long filePk, String filePath, String fileName, String originalFileName, int type, Item item) {
        this.filePk = filePk;
        this.filePath = filePath;
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.type = type;
        this.item = item;
    }
}
