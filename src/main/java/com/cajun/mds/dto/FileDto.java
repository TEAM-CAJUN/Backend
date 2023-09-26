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

import java.util.List;
import java.util.stream.Collectors;


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

    @Getter
    @Setter
    @Schema(name = "FileResponseDto", description = "file 응답 dto")
    public static class Response{
        private Long filePk;
        private String filePath;
        private int type;
        private Long itemPk;

        @Builder
        public Response(File file){
            this.filePk = file.getFilePk();
            this.filePath = file.getFilePath();
            this.type = file.getType();
            this.itemPk = file.getItem().getItemPk();
        }

        public static List<Response> ResponseList(List<File> fileList){
            for(File f : fileList) System.out.println(f);
            List<Response> list = fileList.stream()
                    .map(FileDto.Response :: new)
                    .collect(Collectors.toList());

            return list;
        }
    }
}
