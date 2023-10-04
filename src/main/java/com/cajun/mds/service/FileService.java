package com.cajun.mds.service;

import com.cajun.mds.domain.File;
import com.cajun.mds.domain.Item;
import com.cajun.mds.dto.FileDto;
import com.cajun.mds.repository.FileRepository;
import com.cajun.mds.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final ItemRepository itemRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Transactional
    public Long saveFile(FileDto.Request request) throws IOException {
        MultipartFile file = request.getMultipartFile();
        if(file.isEmpty()) return null;

        String origName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf(".")); // 확장자 추출
        String savedName = uuid + extension;
        String savedPath = fileDir + savedName;

        Item item = Item.builder()
                .itemPk(request.getItemPk())
                .build();

        File f = File.builder()
                .fileName(savedName)
                .filePath(savedPath)
                .originalFileName(origName)
                .type(request.getType())
                .item(item)
                .build();

        file.transferTo(new java.io.File(savedPath));

        return fileRepository.save(f).getFilePk();
    }

    @Transactional
    public List<FileDto.Response> getFiles(Long itemPk) {
        return FileDto.Response.ResponseList(fileRepository.findByItem_ItemPk(itemPk));
    }

    @Transactional
    public FileDto.Response getFile(Long filePk) {
        File file = fileRepository.findById(filePk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다"));
        return new FileDto.Response(file);
    }

    @Transactional
    public void deleteFile(Long filePk) {
        fileRepository.deleteById(filePk);
    }

}
