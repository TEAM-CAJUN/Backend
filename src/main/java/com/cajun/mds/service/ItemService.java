package com.cajun.mds.service;

import com.cajun.mds.domain.Item;
import com.cajun.mds.dto.ItemDto;
import com.cajun.mds.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
