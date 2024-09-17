package com.EEIT85.bunnySugar.service;

import com.EEIT85.bunnySugar.dto.ImageDto;
import com.EEIT85.bunnySugar.entity.Img;
import com.EEIT85.bunnySugar.repository.UploadImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadImgService {

    @Autowired
    UploadImgRepository uploadImgRepository;

    public void uploadImg(ImageDto imageDto) {

        Img imgEntity = new Img();
        imgEntity.setImg(imageDto.getImg());

        uploadImgRepository.save(imgEntity);

    }

    public String getImageById(Long id) {
        return uploadImgRepository.getImageById(id);
    }
}
