package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberJobDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberJobDAO {
    MemberJobDTO findMemberJobByMemberNo(Long memberNo);
}
