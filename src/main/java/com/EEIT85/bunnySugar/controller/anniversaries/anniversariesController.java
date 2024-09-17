package com.EEIT85.bunnySugar.controller.anniversaries;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesCheckDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
//import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesUpdateDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesUpdateDto;
import com.EEIT85.bunnySugar.entity.Anniversaries;
import com.EEIT85.bunnySugar.service.anniversaries.AnniversariesService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/anniversaries")
@RestController
public class anniversariesController {
    @Autowired
    AnniversariesService anniversariesService;

    @PostMapping("/check")
    public String checkAnniversaries(HttpServletRequest request,
                                                     @RequestBody AnniversariesCheckDto anniversariesCheckDto) throws MessagingException {
        return anniversariesService.calculateDateDifference(anniversariesCheckDto);

    }

    @GetMapping("/all")
    public List<AnniversariesSelectDto> getAllById(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return anniversariesService.getAllById(userId);
    }

    @GetMapping
    public Page<Anniversaries> getAnniversariesPage(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Long userId = (Long) request.getAttribute("userId");
        Pageable pageable = PageRequest.of(page, size);
        return anniversariesService.getAnniversariesPaginated(userId, pageable);
    }

    @PostMapping
    public ResponseEntity<String> insertAnniversaries(HttpServletRequest request,
                                                      @RequestBody AnniversariesInsertDto anniversariesInsertDto) {

        Long userId = (Long) request.getAttribute("userId");
        anniversariesService.insertAnniversaries(anniversariesInsertDto, userId);
        return ResponseEntity.ok("成功新增");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnniversaries(HttpServletRequest request, @PathVariable Long id) {
        anniversariesService.deleteAnniversaries(id);
        return ResponseEntity.ok("成功刪除");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnniversaries(HttpServletRequest request, @PathVariable Long id ,
                                                      @RequestBody AnniversariesUpdateDto anniversariesUpdateDto) {
        anniversariesService.updateAnniversaries(id, anniversariesUpdateDto);
        return ResponseEntity.ok("成功更新");
    }
}
