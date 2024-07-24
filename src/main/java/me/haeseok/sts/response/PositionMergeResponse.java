package me.haeseok.sts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.PositionDetailDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionMergeResponse {
    private Integer no;
    private String name;
    private List<PositionDetailDTO> positionDetailList;
}
