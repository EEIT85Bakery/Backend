package com.EEIT85.bunnySugar.controller;


import com.EEIT85.bunnySugar.dto.ImageDto;
import com.EEIT85.bunnySugar.service.UploadImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload")
public class UploadImgController {

    @Autowired
    UploadImgService uploadImgService;

    @PostMapping
    public void uploadImg(@RequestBody ImageDto imageDto){

        uploadImgService.uploadImg(imageDto);
        System.out.println("ok");
    }

    @GetMapping("/{id}")
    public String getImage(@PathVariable Long id) {
        String imageBase64 = uploadImgService.getImageById(id);
        if (imageBase64 != null) {
            return imageBase64; // 返回 Base64 字符串
        } else {
            throw new RuntimeException("Image not found");
        }
    }

}
