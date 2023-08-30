package com.cajun.mds.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoPk;

    @NotNull
    @Size(max = 300)
    private String title;

    @NotNull
    @Size(max = 5000)
    private String content;

    @CreationTimestamp
    private LocalDateTime time;

    @Builder
    public Info(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
