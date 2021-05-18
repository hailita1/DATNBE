package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<Iterable<Image>> getAllImage() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> createNewImage(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image1 -> {
            image1.setId(image1.getId());
            image1.setLink(image.getLink());
            image1.setStatus(image.getStatus());
            image1.setHouseImage(image.getHouseImage());
            return new ResponseEntity<>(imageService.save(image1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable Long id) {
        Optional<Image> categoryOptional = imageService.findById(id);
        return categoryOptional.map(image1 -> {
            imageService.remove(id);
            return new ResponseEntity<>(image1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
