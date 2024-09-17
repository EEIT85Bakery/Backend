package com.EEIT85.bunnySugar.service.game;

import com.EEIT85.bunnySugar.dto.game.GameDetailsDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.GameRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // 開始遊戲邏輯
    public GameDetailsDto startGame(Long id) {
        Users user = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + id));

        GameDetailsDto gameDetailsDto = new GameDetailsDto();

        // 檢查用戶的遊戲次數是否有效
        if (user.getGameTimes() == null || user.getGameTimes() <= 0) {
            gameDetailsDto.setGameTimes(0);  // 次數為 0，不能開始遊戲
            return gameDetailsDto;
        } else {
            // 不在這裡扣減遊戲次數，等待遊戲結束時再扣減
            gameDetailsDto.setGameTimes(user.getGameTimes()); // 返回當前遊戲次數
            return gameDetailsDto;
        }
    }

    // 結束遊戲邏輯（在遊戲結束時扣減次數）
    public GameDetailsDto endGame(Long id, GameDetailsDto result) {
        // 查找用戶
        Users user = gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found for id: " + id));

        // 檢查 result 是否有效
        if (result == null || result.getEarnedCoins() == null) {
            System.out.println("Invalid result data.");
            return null;
        }

        // 減少遊戲次數
        int updatedGameTimes = user.getGameTimes() - 1;

        // 更新用戶的 bunnyCoin
        int updatedBunnyCoins = user.getBunnyCoin() + result.getEarnedCoins();

        // 保存更新
        user.setGameTimes(updatedGameTimes);
        user.setBunnyCoin(updatedBunnyCoins);
        gameRepository.save(user);

        // 返回更新後的 GameDetailsDto
        GameDetailsDto gameDetailsDto = new GameDetailsDto();
        gameDetailsDto.setGameTimes(updatedGameTimes); // 更新後的遊戲次數
        gameDetailsDto.setEarnedCoins(result.getEarnedCoins()); // 前端傳來的獲得金幣數量
        gameDetailsDto.setBunnyCoins(updatedBunnyCoins); // 更新後的 BunnyCoin
        return gameDetailsDto;
    }


    // 抓次數
    public Integer getGameTimes(Long userId) {
        // 通過 Repository 查詢 gameTimes
        Integer gameTimes = gameRepository.findGameTimesByUserId(userId);
        if (gameTimes == null) {
            throw new IllegalArgumentException("User not found or no game times available for id: " + userId);
        }
        return gameTimes;
    }

}


