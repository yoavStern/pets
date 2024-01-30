package com.signalpet.pets.rest.controller;

import com.signalpet.pets.repository.S3StorageService;
import com.signalpet.pets.service.XRayService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/xray")
public class XRayController {
    private final XRayService xRayService;
    private final S3StorageService storageService;

    public XRayController(XRayService xRayService,S3StorageService storageService) {
        this.xRayService = xRayService;
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<Integer> saveXRay(@RequestParam("file") MultipartFile file) throws IOException {
        Integer petDTO = xRayService.save(file);
        return ResponseEntity.ok(petDTO);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity getXray(@PathVariable Integer id) throws IOException {

        Resource resource = new InputStreamResource(storageService.downloadFileById(xRayService.getXRayImageKey(id)));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getXrayDTO(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok().body(xRayService.getXRayDTO(id));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePet(@PathVariable Integer id) {
        xRayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

