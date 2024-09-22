package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.dto.users.MembeAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.MemberAdminDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberAdminService {
    @Autowired
    private UserRepository userRepository;

    // 查詢所有會員並返回分頁結果
    public Page<MemberAdminDto> getAllMembers(Pageable pageable) {
        Page<Users> membersPage = userRepository.findAll(pageable);
        return membersPage.map(this::convertToAdminMemberDto);  // 使用 map 將 Users 轉換為 AdminMemberDto
    }

    // 根據ID查詢會員
    public MemberAdminDto getMemberById(UUID id) {
        return userRepository.findById(id).map(this::convertToAdminMemberDto).orElse(null);
    }

    // 更新會員資料
    public boolean updateMember(UUID id, MembeAdminUpdateDto updatedMemberDto) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();

            // 根據DTO的資料更新會員資料
            user.setEmail(updatedMemberDto.getEmail() != null ? updatedMemberDto.getEmail() : user.getEmail());
            user.setName(updatedMemberDto.getName() != null ? updatedMemberDto.getName() : user.getName());
            user.setPhone(updatedMemberDto.getPhone() != null ? updatedMemberDto.getPhone() : user.getPhone());
            user.setGender(updatedMemberDto.getGender() != null ? updatedMemberDto.getGender() : user.getGender());
            user.setBirthday(updatedMemberDto.getBirthday() != null ? updatedMemberDto.getBirthday() : user.getBirthday());


            // 更新時間
            user.setUpdateTime(LocalDateTime.now());

            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // 將Users轉換為AdminMemberDto
    private MemberAdminDto convertToAdminMemberDto(Users user) {
        MemberAdminDto memberAdminDto = new MemberAdminDto();
        memberAdminDto.setId(user.getId());
        memberAdminDto.setAccount(user.getAccount());
        memberAdminDto.setEmail(user.getEmail());
        memberAdminDto.setName(user.getName());
        memberAdminDto.setPhone(user.getPhone());
        memberAdminDto.setGender(user.getGender());
        memberAdminDto.setBirthday(user.getBirthday());
        memberAdminDto.setActive(user.getActive());
        memberAdminDto.setCreateTime(user.getCreateTime());
        memberAdminDto.setUpdateTime(user.getUpdateTime());
        return memberAdminDto;
    }
}
