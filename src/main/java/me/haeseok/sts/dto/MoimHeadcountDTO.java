package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimHeadcountDTO {
    private Long no;
    private Integer personnel;
    private Integer positionDetailNo;
    private Long moimNo;
}
