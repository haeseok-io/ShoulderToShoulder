package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimLanguageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MoimLanguageDAO {
    void addOne(MoimLanguageDTO dto);
    List<MoimLanguageDTO> getMoimLanguageByMoimNo(Long moimNo);
}
