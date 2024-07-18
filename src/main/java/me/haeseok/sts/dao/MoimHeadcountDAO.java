package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimHeadcountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MoimHeadcountDAO {
    void addOne(MoimHeadcountDTO dto);
    List<MoimHeadcountDTO> getMoimHeadcountByMoimNo(Long moimNo);
}
