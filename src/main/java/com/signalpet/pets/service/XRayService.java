package com.signalpet.pets.service;

import com.signalpet.pets.model.XRay;
import com.signalpet.pets.repository.StorageService;
import com.signalpet.pets.repository.XRayRepository;
import com.signalpet.pets.rest.dto.XRayDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class XRayService {

    private final StorageService storageService;
    private final XRayRepository xRayRepository;

    public XRayService(StorageService storageService, XRayRepository xRayRepository) {
        this.storageService = storageService;
        this.xRayRepository = xRayRepository;
    }

    public void deleteById(Integer id) {
        XRay referenceById = xRayRepository.getReferenceById(id);
        storageService.deleteFile(referenceById.getName());
        xRayRepository.deleteById(id);

    }

    public Integer save(MultipartFile file) throws IOException {
        XRay xRayEntity = new XRay();
        String originalFilename = file.getOriginalFilename();
        storageService.uploadFile(originalFilename, file);
        xRayEntity.setName(originalFilename);
        try {
            xRayRepository.save(xRayEntity).getId();
        } catch (Exception ex) {
            // rollback
            storageService.deleteFile(file.getName());
            throw new RuntimeException("failed to save xray entity to db");
        }
        return xRayEntity.getId();
    }

    public InputStream getXRayImage(String name) throws IOException {

        return storageService.downloadFileById(name);
    }

    public XRayDTO getXRayDTO(Integer id) {
        XRay referenceById = xRayRepository.getReferenceById(id);

        return new XRayDTO(referenceById.getName(),referenceById.getCreatedDate());
    }

    @Transactional
    public String getXRayImageKey(Integer id) {
        Optional<XRay> byId = xRayRepository.findById(id);
        if (byId.isEmpty()){
            throw new RuntimeException("not found");
        }
        return byId.get().getName();
    }
}
