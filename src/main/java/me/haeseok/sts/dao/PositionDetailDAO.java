package me.haeseok.sts.dao;

import me.haeseok.sts.dto.PositionDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionDetailDAO {
    PositionDetailDTO getPositionDetailByNo(Integer no);
    List<PositionDetailDTO> getPositionDetailByPositionNo(Integer positionNo);
}
