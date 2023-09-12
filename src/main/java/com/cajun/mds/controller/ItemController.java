package com.cajun.mds.controller;

import com.cajun.mds.domain.RegionKey;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @Operation(summary = "매물 등록", description = "매물을 등록함")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ItemDto.Request.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/item/register")
    public void post(@RequestBody @Valid ItemDto.Request request){
        itemService.register(request);
    }

    @Operation(summary = "매물 조회", description = "지역 정보(구, 동)을 통해 매물 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ItemDto.Request.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/item/get")
    public List<ItemDto.Response> getItems(@RequestParam(name = "gu") int regionCode,
                                          @RequestParam(name = "dong") int dongCode){
        return itemService.getItems(regionCode, dongCode);
    }

    // file upload 구현 후 수정 필요!!
    @Operation(summary = "매물 조회 세부", description = "itemPk로 매물 상세조회.. File 구현 후 수정 필요")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ItemDto.Request.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/item/get/{itemPk}")
    public ItemDto.Response getItem(@PathVariable Long itemPk){
        return itemService.getItem(itemPk);
    }

    @Operation(summary = "매물 삭제", description = "매물 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/item/delete/{itemPk}")
    public void deleteItem(@PathVariable Long itemPk) {
        itemService.deleteItem(itemPk);
    }
    @Operation(summary = "매물 수정", description = "매물 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/item/update/{itemPk}")
    public Long updateItem(@PathVariable Long itemPk, @RequestBody @Valid ItemDto.Request request){
        return itemService.updateItem(itemPk, request);
    }

}
