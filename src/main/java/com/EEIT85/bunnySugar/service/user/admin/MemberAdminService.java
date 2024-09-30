package com.EEIT85.bunnySugar.service.user.admin;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberAdminService {
    @Autowired
    private UserRepository userRepository;

    // 查詢所有會員並返回DTO的分頁結果
    public Page<MemberAdminDto> getAllMembers(Pageable pageable) {
        return userRepository.findAllMemberAdminSelectDto(pageable);
    }

    // 根據ID查詢會員並返回DTO
    public MemberAdminDto getMemberById(Long id) {
        return userRepository.findMemberAdminSelectDtoById(id);
    }

    // 更新會員的 userVip
    public boolean updateMemberVip(Long id, MemberAdminUpdateDto updatedMemberDto) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();

            //更新 userVip 屬性
            user.setUserVip(updatedMemberDto.getUserVip());
            //更新時間
            user.setUpdateTime(LocalDateTime.now());

            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

}
