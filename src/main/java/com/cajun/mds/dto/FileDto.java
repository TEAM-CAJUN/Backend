package com.cajun.mds.dto;

import com.cajun.mds.domain.File;
import com.cajun.mds.domain.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


public class FileDto {
    @Getter
    @Setter
    @Builder
    @Schema(name = "FileRequestDto", description = "file 요청 dto")
    @ToString
    public static class Request{

        private MultipartFile multipartFile;

        @NotNull
        private int type;

        @NotNull
        private Long itemPk;
    }

    @Schema(name = "FileResponseDto", description = "file 응답 dto")
    public static class Response{

    }
}
