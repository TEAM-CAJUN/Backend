package com.cajun.mds.controller;

import com.cajun.mds.domain.File;
import com.cajun.mds.domain.Item;
import com.cajun.mds.dto.FileDto;
import com.cajun.mds.service.FileService;
import com.cajun.mds.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final FileService fileService;
    private final ItemService itemService;

    @Operation(summary = "파일 승인 완료", description = "파일 승인 완료. ")
    @PatchMapping(value = "/admin/approve/{filePk}")
    public void fileApprove(@PathVariable Long filePk) throws IOException {
        FileDto.Response file = fileService.getFile(filePk);
        itemService.approveItem(file.getItemPk(), file.getType());
    }
}
