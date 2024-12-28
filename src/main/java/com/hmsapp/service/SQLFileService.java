package com.hmsapp.service;

import com.hmsapp.entity.SQLFileEntity;
import com.hmsapp.repository.SQLFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SQLFileService {

    @Autowired
    private SQLFileRepository fileRepository;

    public SQLFileEntity saveFile(MultipartFile file) throws Exception {
        SQLFileEntity fileEntity = new SQLFileEntity();

        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setData(file.getBytes());

        return fileRepository.save(fileEntity);
    }
}
