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
    private Integer personnel;
    private PositionDTO position;
    private PositionDetailDTO positionDetail;
}
