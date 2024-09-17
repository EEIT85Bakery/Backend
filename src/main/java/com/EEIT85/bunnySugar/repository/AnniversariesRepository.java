package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
import com.EEIT85.bunnySugar.entity.Anniversaries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnniversariesRepository extends JpaRepository<Anniversaries, Long> {

    @Modifying
    @Query(value = "INSERT INTO anniversaries (anniversary_name, anniversary_date, mail_sent, create_time, update_time, users_id) " +
            "VALUES (:anniversaryName, :anniversaryDate, :mailSent, :createTime, :updateTime, :usersId)", nativeQuery = true)
    void saveAnniversariesAndUsersId(@Param("anniversaryName") String anniversaryName,
                                     @Param("anniversaryDate") LocalDate anniversaryDate,
                                     @Param("mailSent") Boolean mailSent,
                                     @Param("createTime") LocalDateTime createTime,
                                     @Param("updateTime") LocalDateTime updateTime,
                                     @Param("usersId") Long usersId);


    List<Anniversaries> findByUsersId(Long userId);

    Page<Anniversaries> findByUsersId(Long userId, Pageable pageable);

    Optional<Anniversaries> findByIdAndUsersId(Long id, Long usersId);
}
