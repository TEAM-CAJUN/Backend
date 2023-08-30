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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FaqPk;

    @NotNull
    private int category;

    @NotNull
    @Size(max = 100)
    private String question;

    @NotNull
    @Size(max = 100)
    private String answer;

    @Builder
    public Faq(int category, String question, String answer) {
        this.category = category;
        this.question = question;
        this.answer = answer;
    }
}
