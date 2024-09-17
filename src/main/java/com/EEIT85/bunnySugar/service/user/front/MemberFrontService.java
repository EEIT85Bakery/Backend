package com.EEIT85.bunnySugar.service.user.front;

import com.EEIT85.bunnySugar.dto.users.front.MemberFrontDto;
import com.EEIT85.bunnySugar.dto.users.front.MemberFrontUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.exception.MemberNotFoundException;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberFrontService {
    @Autowired
    private UserRepository userRepository;

    public Optional<Users> getMemberInfoById(Long userId) {
        return userRepository.findById(userId);
    }

    // 根據 ID查詢會員資料
    public MemberFrontDto getMemberById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    MemberFrontDto memberFrontDto = new MemberFrontDto();
                    memberFrontDto.setId(user.getId());
                    memberFrontDto.setName(user.getName());
                    memberFrontDto.setGender(user.getGender());
                    memberFrontDto.setPhone(user.getPhone());
                    memberFrontDto.setBirthday(user.getBirthday());
                    memberFrontDto.setEmail(user.getEmail());
                    memberFrontDto.setGameTimes(user.getGameTimes());
                    memberFrontDto.setBunnyCoin(user.getBunnyCoin());
                    return memberFrontDto;
                })
                .orElseThrow(() -> new MemberNotFoundException("會員不存在"));
    }


    // 更新會員資料
    @Transactional
    public boolean updateMember(Long userId, MemberFrontUpdateDto updatedMemberDto) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new MemberNotFoundException("會員不存在"));

        // 更新前端允許更改的字段
        if (updatedMemberDto.getName() != null) {
            user.setName(updatedMemberDto.getName());
        }
        if (updatedMemberDto.getPhone() != null) {
            user.setPhone(updatedMemberDto.getPhone());
        }
        if (updatedMemberDto.getGender() != null) {
            user.setGender(updatedMemberDto.getGender());
        }
        if (updatedMemberDto.getBirthday() != null) {
            user.setBirthday(updatedMemberDto.getBirthday());
        }

        user.setUpdateTime(LocalDateTime.now());

        userRepository.save(user);
        return true; // 返回 true 表示更新成功
    }


    //刪除會員
    public boolean deleteMemberById(Long id) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
