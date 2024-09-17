package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u.gameTimes FROM Users u WHERE u.id = :userId")
    Integer findGameTimesByUserId(@Param("userId") Long userId);
}
