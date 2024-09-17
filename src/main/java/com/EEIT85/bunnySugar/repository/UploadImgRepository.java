package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadImgRepository extends JpaRepository<Img, Long> {
    // 如果你只想要圖片的 URL
    @Query("SELECT i.img FROM Img i WHERE i.id = :id")
    String getImageById(Long id);
}
