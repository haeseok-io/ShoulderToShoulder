package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDetailDAO {
    MemberDetailDTO findMemberDetailByNo(Long no);

    void addMemberDetail(MemberDetailDTO memberDetailDTO);
    void modifyMemberDetail(MemberDetailDTO memberDetailDTO);
}
