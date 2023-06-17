package com.example.filedemo.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class ImageController {
    private Map<Long, byte[]> imageMap = new HashMap<>();

    // REST API to accept an image file and store it in the memory DB
    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Generate a unique ID for the image
            Long imageId = generateImageId();
            
            // Save the image data to the map
            byte[] imageData = file.getBytes();
            imageMap.put(imageId, imageData);

            return ResponseEntity.ok("Image uploaded successfully. Image ID: " + imageId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the image.");
        }
    }

    // Utility method to generate a unique image ID
    private Long generateImageId() {
        // Implement your logic to generate a unique image ID
        // You can use a counter or a random number generator
        // Here, we are using a simple counter for demonstration purposes
        return imageMap.size() + 1L;
    }
    
  

          
        // REST API to retrieve and display the image in the browser
    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            // Retrieve the image data from the map based on the provided ID
            byte[] imageData = imageMap.get(id);

            if (imageData != null) {
                // Read the original image
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
                BufferedImage originalImage = ImageIO.read(inputStream);

                // Resize the image
                int targetWidth = 800; // Set the desired width of the resized image
                int targetHeight = (int) (originalImage.getHeight() * ((double) targetWidth / originalImage.getWidth()));
                Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);

                // Convert the resized image to bytes
                BufferedImage bufferedResizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = bufferedResizedImage.createGraphics();
                graphics.drawImage(resizedImage, 0, 0, null);
                graphics.dispose();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedResizedImage, "jpeg", outputStream);
                byte[] resizedImageData = outputStream.toByteArray();

                // Return the resized image
                return ResponseEntity.ok().body(resizedImageData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
    }

