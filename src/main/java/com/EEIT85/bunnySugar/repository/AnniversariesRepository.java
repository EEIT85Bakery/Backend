package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
import com.EEIT85.bunnySugar.entity.Anniversaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnniversariesRepository extends JpaRepository<Anniversaries, Long> {

    @Modifying
    @Query(value = "INSERT INTO anniversaries (anniversary_name, anniversary_date, mail_sent, create_time, update_time, users_id) " +
            "VALUES (:anniversaryName, :anniversaryDate, :mailSent, :createTime, :updateTime, :usersId)", nativeQuery = true)
    void saveAnniversariesAndUsersId(@Param("anniversaryName") String anniversaryName,
                                     @Param("anniversaryDate") LocalDate anniversaryDate,
                                     @Param("mailSent") Boolean mailSent,
                                     @Param("createTime") LocalDateTime createTime,
                                     @Param("updateTime") LocalDateTime updateTime,
                                     @Param("usersId") UUID usersId);


    List<Anniversaries> findByUsersId(UUID userId);

    Optional<Anniversaries> findByIdAndUsersId(Long id, UUID usersId);
}
