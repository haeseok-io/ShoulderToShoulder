package me.haeseok.sts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.PositionDTO;
import me.haeseok.sts.dto.PositionDetailDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimHeadcountResponse {
    private Long no;
    private PositionDTO position;
    private PositionDetailDTO positionDetail;
    private Integer personnelCount; // 모집인원
    private Integer appliedCount; // 지원자수
    private Integer approvedCount; // 승인자수
}
