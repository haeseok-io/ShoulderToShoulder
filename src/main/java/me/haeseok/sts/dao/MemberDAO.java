package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.dto.MemberIdRangeDTO;
import me.haeseok.sts.request.MemberListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberDAO {
    Long getTotal();
    Long getSearchTotal(MemberListRequest request);
    MemberIdRangeDTO getMemberIdRange();
    MemberDTO getMemberRandom();

    MemberDTO findMemberByNo(Long no);
    MemberDTO findMemberByEmail(String email);
    MemberDTO findMemberByEmailAndProvider(@Param("email") String email, @Param("provider") String provider);
    List<MemberDTO> findSearchList(MemberListRequest request);

    void addMember(MemberDTO memberDTO);
    void modifyMember(MemberDTO memberDTO);
    void dropMember(Long no);

    Boolean emailExist(String email);
    Boolean nicknameExist(String nickname);
}
