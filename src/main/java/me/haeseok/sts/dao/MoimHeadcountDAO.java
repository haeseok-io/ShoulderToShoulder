package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimHeadcountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimHeadcountDAO {
    void addOne(MoimHeadcountDTO dto);
}
