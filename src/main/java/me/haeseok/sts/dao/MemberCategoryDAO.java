package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberCategoryDAO {
    List<MemberCategoryDTO> findMemberCategoryByMemberNo(Long memberNo);
}
