package com.fastcampus.loan.service;

import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("Files Copy Error: ", e);
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path file = Paths.get(uploadPath).resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.isReadable() || resource.exists()) {
                return resource;
            } else {
                throw new BaseException(ResultType.NOT_EXIST);
            }
        } catch (MalformedURLException e) {
            log.error("Files Load Error: ", e);
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(Paths.get(uploadPath), 1).filter((path -> !path.equals(Paths.get(uploadPath))));
        } catch (Exception e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }
}
