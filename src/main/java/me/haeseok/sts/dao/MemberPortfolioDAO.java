package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MemberPortfolioDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberPortfolioDAO {
    List<MemberPortfolioDTO> findMemberPortfolioByMemberNo(Long memberNo);
}
