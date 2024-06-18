package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface MemberDAO {
    void addMember(MemberDTO memberDTO);
    MemberDTO findMemberNo(Long no);
    MemberDTO findMemberEmail(String email);
    MemberDTO findMemberEmailAsProvider(@Param("email") String email, @Param("provider") String provider);
    Boolean emailExist(String email);
    Boolean nicknameExist(String nickname);

    void dropMember(Long no);

    default void addMemberWithDetail(MemberDTO memberDTO) {
        addMember(memberDTO);
    }


}
