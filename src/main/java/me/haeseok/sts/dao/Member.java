package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Member {
    void addMember(MemberDTO memberDTO);
    MemberDTO readMember(Long no);
    void dropMember(Long no);

    default void addMemberWithDetail(MemberDTO memberDTO) {
        addMember(memberDTO);
    }


}
