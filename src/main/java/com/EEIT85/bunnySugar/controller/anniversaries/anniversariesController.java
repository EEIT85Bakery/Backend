package com.EEIT85.bunnySugar.controller.anniversaries;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesUpdateDto;
import com.EEIT85.bunnySugar.service.AnniversariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/anniversaries")
@RestController
public class anniversariesController {

    @Autowired
    AnniversariesService anniversariesService;

    // 修改: 添加了 @PathVariable UUID userId 參數
    @GetMapping("/{userId}")
    public List<AnniversariesSelectDto> getAllById(@PathVariable UUID userId) {
        return anniversariesService.getAllById(userId);
    }

    // 修改: 添加了 @PathVariable UUID userId 參數
    @PostMapping("/{userId}")
    public ResponseEntity<String> insertAnniversaries(@PathVariable UUID userId,
                                                      @RequestBody AnniversariesInsertDto anniversariesInsertDto) {
        anniversariesService.insertAnniversaries(anniversariesInsertDto, userId);
        return ResponseEntity.ok("成功新增");
    }

    // 修改: 添加了 @PathVariable UUID userId 參數
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteAnniversaries(@PathVariable UUID userId,
                                                      @PathVariable Long id) {
        anniversariesService.deleteAnniversaries(userId, id);
        return ResponseEntity.ok("成功刪除");
    }

    // 修改: 添加了 @PathVariable UUID userId 參數
    @PutMapping("/{userId}/{id}")
    public ResponseEntity<String> updateAnniversaries(@PathVariable UUID userId,
                                                      @PathVariable Long id,
                                                      @RequestBody AnniversariesUpdateDto anniversariesUpdateDto) {
        anniversariesService.updateAnniversaries(userId, id, anniversariesUpdateDto);
        return ResponseEntity.ok("成功更新");
    }
}
