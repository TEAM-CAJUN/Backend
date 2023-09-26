package com.cajun.mds.repository;

import com.cajun.mds.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByItem_ItemPk(Long itemPk);
}
