package me.haeseok.sts.service;

import me.haeseok.sts.dto.PositionDTO;
import me.haeseok.sts.dto.PositionDetailDTO;
import me.haeseok.sts.response.PositionMergeResponse;

import java.util.List;

public interface PositionService {
    List<PositionDTO> readPositionList();
    List<PositionMergeResponse> readPositionMergeList();
    List<PositionDetailDTO> readPositionDetailByPositionNo(Integer positionNo);
}
