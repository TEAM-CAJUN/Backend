package com.cajun.mds.service;

import com.cajun.mds.domain.Item;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
//    private final MemberRepository memberRepository;

    public void register(ItemDto.Request request) {
//        Member member = memberRepository.findById(request.getMemberPk());
        Item item = Item.builder()
//                .member(member)
                .region(request.getRegion())
                .price(request.getPrice())
                .totalSquare(request.getTotalSquare())
                .unitSquare(request.getUnitSquare())
                .isDeal(request.isDeal())
                .addressDetail(request.getAddressDetail())
                .isInsurance(request.getIsInsurance())
                .description(request.getDescription())
                .build();
        itemRepository.save(item);
    }

    public List<ItemDto.Response> getItems(int regionCode, int dongCode) {
        return ItemDto.Response.ResponseList(itemRepository.findByRegion_RegionCodeAndRegion_DongCode(regionCode, dongCode));
    }

    public ItemDto.Response getItem(Long itemPk) {
        Item item = itemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다"));

        return new ItemDto.Response(item);
    }

    public void deleteItem(Long itemPk) {
        itemRepository.deleteById(itemPk);
    }

    @Transactional
    public Long updateItem(Long itemPk, ItemDto.Request request) {
        Item item = itemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다"));
        item.updateItem(request.getPrice(), request.getAddressDetail(), request.isDeal(),
                request.getIsInsurance(), request.getDescription(), request.getIsLoans(),
                request.getIsPaper(), request.getIsBpaper(), request.getIsPhoto());
        return itemRepository.save(item).getItemPk();
    }

    @Transactional
    public int predict(boolean deal, String description, int loans, int paper, int bpaper, int photo, int insurance) {
        int level = 0;
        if(deal) level++;
        if(description != null && (description.length()) > 10) level++;
        if(loans == 1 || loans == 2) level++;
        if(paper == 1 || paper == 2) level++;
        if(bpaper == 1 || bpaper == 2) level++;
        if(photo == 1 || photo == 2) level++;
        if(insurance == 1 || insurance == 2) level++;

        if(level <= 3 && level > 0) level = 1;
        if(level > 3) level -= 2;
        return level;
    }

    @Transactional
    public Long approveItem(Long itemPk, int type) {
        Item item = itemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다"));
        if(type == 0) item.setIsBpaper(2);
        else if(type == 1) item.setIsBpaper(2);
        else if(type == 2) item.setIsPaper(2);
        else new IllegalArgumentException("잘못된 타입입니다.");
        return itemRepository.save(item).getItemPk();
    }

    @Transactional
    public Long rejectItem(Long itemPk, int type) {
        Item item = itemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다"));
        if(type == 0) item.setIsBpaper(3);
        else if(type == 1) item.setIsBpaper(3);
        else if(type == 2) item.setIsPaper(3);
        else new IllegalArgumentException("잘못된 타입입니다.");
        return itemRepository.save(item).getItemPk();
    }

    @Transactional
    public Long cancelItem(Long itemPk, int type) {
        Item item = itemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다"));
        if(type == 0) item.setIsBpaper(4);
        else if(type == 1) item.setIsBpaper(4);
        else if(type == 2) item.setIsPaper(4);
        else new IllegalArgumentException("잘못된 타입입니다.");
        return itemRepository.save(item).getItemPk();
    }
}
