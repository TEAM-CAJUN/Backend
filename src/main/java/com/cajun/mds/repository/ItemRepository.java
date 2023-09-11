package com.cajun.mds.repository;

import com.cajun.mds.domain.Item;
import com.cajun.mds.dto.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByRegion_RegionCodeAndRegion_DongCode(int regionCode, int dongCode);
}
