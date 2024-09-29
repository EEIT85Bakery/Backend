package com.EEIT85.bunnySugar.service.user.front;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.front.MemberFrontUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberFrontService {
    @Autowired
    private UserRepository userRepository;

    // 更新會員資料(不能更改欄位回傳原值)
    public boolean updateMember(Long id, MemberFrontUpdateDto updatedMemberDto) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();

            user.setName(updatedMemberDto.getName() != null ? updatedMemberDto.getName() : user.getName());
            user.setPhone(updatedMemberDto.getPhone() != null ? updatedMemberDto.getPhone() : user.getPhone());
            user.setGender(updatedMemberDto.getGender() != null ? updatedMemberDto.getGender() : user.getGender());
            user.setBirthday(updatedMemberDto.getBirthday() != null ? updatedMemberDto.getBirthday() : user.getBirthday());

            // 不允許修改的屬性值不變
            user.setEmail(user.getEmail());
            user.setGameTimes(user.getGameTimes());
            user.setBunnyCoin(user.getBunnyCoin());
            // 更新時間
            user.setUpdateTime(LocalDateTime.now());

            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
