package me.haeseok.sts.dao;

import me.haeseok.sts.dto.PositionDTO;
import me.haeseok.sts.dto.PositionDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionDAO {
    List<PositionDTO> getPositionList();
    List<PositionDetailDTO> getPositionDetailList();
    List<PositionDetailDTO> getPositionDetailListByPositionNo(Integer positionNo);
}
