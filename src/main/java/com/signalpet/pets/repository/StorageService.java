package com.signalpet.pets.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface StorageService {

    void uploadFile(String key, MultipartFile file) throws IOException;

    void deleteFile(String name);

    InputStream downloadFileById(String keyName) throws IOException;

    }
