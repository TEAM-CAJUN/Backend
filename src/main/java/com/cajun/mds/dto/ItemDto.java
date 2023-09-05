package com.cajun.mds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ItemDto {
    @Getter
    @Setter
    @Builder
    public static class Request{
//        @NotNull
//        private Long memberPk;
        @NotNull
        private int regionPk;
        @NotBlank
        private String addressDetail;
        @NotNull
        private int price;
        @NotNull
        private double totalSquare;
        @NotNull
        private double unitSquare;
        private boolean isDeal;
        private int isInsurance;
        private String description;
        private int isLoans;
        private int paperFile;
        private int bpaperFile;
        private int photoFile;
    }
    public static class Response{

    }
}
