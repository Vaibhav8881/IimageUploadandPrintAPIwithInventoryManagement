package com.example.filedemo.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageRetrievalController {
    private Map<Long, byte[]> imageMap;

    // REST API to retrieve and display the image in the browser
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            // Retrieve the image data from the map based on the provided ID
            byte[] imageData = imageMap.get(id);
            
            // If image data exists, set appropriate headers and return the image data in the ResponseEntity
            if (imageData != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Set appropriate Content-Type based on your image type
                
                return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

