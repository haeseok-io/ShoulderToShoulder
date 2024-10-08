package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.PositionDAO;
import me.haeseok.sts.dao.PositionDetailDAO;
import me.haeseok.sts.dto.PositionDTO;
import me.haeseok.sts.dto.PositionDetailDTO;
import me.haeseok.sts.response.PositionMergeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImple implements PositionService {
    private final PositionDAO positionDAO;
    private final PositionDetailDAO positionDetailDAO;

    @Override
    public List<PositionDTO> readPositionList() {
        return positionDAO.getPositionList();
    }

    @Override
    public List<PositionMergeResponse> readPositionMergeList() {
        return positionDAO.getPositionList().stream().map(position -> {
            return PositionMergeResponse.builder()
                    .no(position.getNo())
                    .name(position.getName())
                    .positionDetailList(positionDetailDAO.getPositionDetailByPositionNo(position.getNo()))
                    .build();
        }).toList();
    }

    @Override
    public List<PositionDetailDTO> readPositionDetailByPositionNo(Integer positionNo) {
        return positionDetailDAO.getPositionDetailByPositionNo(positionNo);
    }
}
