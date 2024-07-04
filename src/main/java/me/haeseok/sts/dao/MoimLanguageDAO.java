package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimLanguageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimLanguageDAO {
    void addOne(MoimLanguageDTO dto);
}
