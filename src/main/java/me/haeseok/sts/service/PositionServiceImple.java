package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.PositionDAO;
import me.haeseok.sts.dto.PositionDTO;
import me.haeseok.sts.dto.PositionDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImple implements PositionService {
    private final PositionDAO positionDAO;

    @Override
    public List<PositionDTO> readPositionList() {
        return positionDAO.getPositionList();
    }

    @Override
    public List<PositionDetailDTO> readPositionDetailList() {
        return positionDAO.getPositionDetailList();
    }

    @Override
    public List<PositionDetailDTO> readPositionDetailListByPositionNo(Integer positionNo) {
        return positionDAO.getPositionDetailListByPositionNo(positionNo);
    }
}
