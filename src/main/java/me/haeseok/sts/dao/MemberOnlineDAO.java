package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberOnlineDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberOnlineDAO {
    MemberOnlineDTO findMemberOnlineByMemberNo(Long memberNo);
}
