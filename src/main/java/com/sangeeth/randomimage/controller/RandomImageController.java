package com.sangeeth.randomimage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class RandomImageController {
    @Autowired
    private RestTemplate restTemplate;

    String randomImageURL = "https://picsum.photos/";
    String resolution = "200/300";

    @GetMapping(value = "/", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getRandomImage() throws IOException {
        return restTemplate.getForObject
                (randomImageURL+resolution, byte[].class);
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getRandomImage(@PathVariable(value = "id")  String id) throws IOException {
        ResponseEntity<byte[]> response = restTemplate.getForEntity(randomImageURL+"id/"+id+resolution, byte[].class);
        return response.getBody();
    }
}
