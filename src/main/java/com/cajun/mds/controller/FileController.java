package com.cajun.mds.controller;

import com.cajun.mds.dto.FileDto;
import com.cajun.mds.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
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
}


