package com.EEIT85.bunnySugar.controller.game;

import com.EEIT85.bunnySugar.dto.game.GameDetailsDto;
import com.EEIT85.bunnySugar.service.game.GameService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // 結合遊戲開始結束
    @PostMapping("/play")
    public ResponseEntity<GameDetailsDto> playGame(HttpServletRequest request, @RequestBody(required = false) GameDetailsDto result) {
        Long userId = (Long) request.getAttribute("userId");

        // 確保 userId 不為 null，否則返回 BAD REQUEST
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        System.out.println("Playing game for userId: " + userId);
        GameDetailsDto dto;
        if (result == null) {
            try {
                dto = gameService.startGame(userId);
                if (dto == null) {
                    System.out.println("Game start failed. Service returned null.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
                return ResponseEntity.ok(dto);
            } catch (Exception e) {
                System.out.println("Error starting game: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            try {
                dto = gameService.endGame(userId, result);
                if (dto == null) {
                    System.out.println("Game end failed. Service returned null.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
                return ResponseEntity.ok(dto);
            } catch (Exception e) {
                System.out.println("Error ending game: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }

    @GetMapping("/times")
    public ResponseEntity<GameDetailsDto> getGameTimes(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            Integer gameTimes = gameService.getGameTimes(userId);
            GameDetailsDto gameDetailsDto = new GameDetailsDto();
            gameDetailsDto.setGameTimes(gameTimes);
            return ResponseEntity.ok(gameDetailsDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//    @GetMapping("/start")
//    public ResponseEntity<String> startGame(HttpServletRequest request) {
//        Long userId = (Long) request.getAttribute("userId");
//        GameDetailsDto dto = gameService.startGame(userId);
//        if (dto.getGameTimes() > 0) {
//            return ResponseEntity.ok("User has enough game times!");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough game times!");
//        }
//    }
//
//
//    @PutMapping("/end")
//    public ResponseEntity<String> endGame(HttpServletRequest request, @RequestBody GameDetailsDto result) {
//        Long userId = (Long) request.getAttribute("userId"); // 從 request 中獲取 userId
//        Integer gameTimes = gameService.endGame(userId, result);
//        if (gameTimes != null && gameTimes >= 0) {
//            return ResponseEntity.ok("Game ended successfully! Remaining game times: " + gameTimes);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error ending the game or insufficient game times.");
//        }
//    }



}

