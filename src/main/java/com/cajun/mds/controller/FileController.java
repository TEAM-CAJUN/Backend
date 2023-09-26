package com.cajun.mds.controller;

import com.cajun.mds.dto.FileDto;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FileController {

    private final FileService fileService;
    @Operation(summary = "파일 업로드", description = "이미지 파일 업로드")
    @PostMapping(value = "/file/upload", consumes = "multipart/form-data")
    public String fileUpload(FileDto.Request file) throws IOException {
        System.out.println(file);
        if(file.getMultipartFile().isEmpty()){
            System.out.println("file is empty");
        }
        System.out.println(file);
        fileService.saveFile(file);
        return "file uploaded!";
    }

    @Operation(summary = "파일 조회", description = "매물 pk로 매물 관련 파일 파일 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = FileDto.Response.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/file/get/{itemPk}")
    public List<FileDto.Response> getFiles(@PathVariable Long itemPk){
        return fileService.getFiles(itemPk);
    }

}


