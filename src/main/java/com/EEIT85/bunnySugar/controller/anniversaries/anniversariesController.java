package com.EEIT85.bunnySugar.controller.anniversaries;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.entity.Anniversaries;
import com.EEIT85.bunnySugar.service.AnniversariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/anniversaries")
@RestController
public class anniversariesController {
    Long usersId = 1L;
    @Autowired
    AnniversariesService anniversariesService;

    @GetMapping
    public List<AnniversariesSelectDto> getAllById() {
        return anniversariesService.getAllById(usersId);
    }

    @PostMapping
    public ResponseEntity<String> insertAnniversaries(@RequestBody AnniversariesInsertDto anniversariesInsertDto) {
        anniversariesService.insertAnniversaries(anniversariesInsertDto, usersId);
        return ResponseEntity.ok("成功新增");
    }

}
