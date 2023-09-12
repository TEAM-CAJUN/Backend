package com.cajun.mds.dto;

import com.cajun.mds.domain.Item;
import com.cajun.mds.domain.Region;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDto {
    @Getter
    @Setter
    @Builder
    public static class Request{
//        @NotNull
//        private Long memberPk;
        @NotNull
        private Region region;
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
        private int isPaper;
        private int isBpaper;
        private int isPhoto;
    }

    @Getter
    @Setter
    public static class Response{
        private Long itemPk;
        private String addressDetail;
        private int price; // 전세가
        private double totalSquare; // 공급면적
        private double unitSquare; // 단일면적
        private boolean isDeal;
        private int isInsurance;
        private String description;
        private int isLoans;
        private int isPaper;
        private int isBpaper;
        private int isPhoto;
        private Region region;

        @Builder
        public Response(Item item){
            this.itemPk = item.getItemPk();
            this.addressDetail = item.getAddressDetail();
            this.price = item.getPrice();
            this.totalSquare = item.getTotalSquare();
            this.unitSquare = item.getUnitSquare();
            this.isDeal = item.isDeal();
            this.isInsurance = item.getIsInsurance();
            this.description = item.getDescription();
            this.isLoans = item.getIsLoans();
            this.isPaper = item.getIsPaper();
            this.isBpaper = item.getIsBpaper();
            this.isPhoto = item.getIsPhoto();
            this.region = item.getRegion();
        }
        public static List<Response> ResponseList(List<Item> itemList){
            return itemList.stream()
                    .map(ItemDto.Response :: new)
                    .collect(Collectors.toList());
        }
    }
}
