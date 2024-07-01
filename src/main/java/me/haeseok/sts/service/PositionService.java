package me.haeseok.sts.service;

import me.haeseok.sts.dto.PositionDTO;
import me.haeseok.sts.dto.PositionDetailDTO;

import java.util.List;

public interface PositionService {
    List<PositionDTO> readPositionList();
    List<PositionDetailDTO> readPositionDetailList();
    List<PositionDetailDTO> readPositionDetailListByPositionNo(Integer positionNo);
}
