package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJobDTO {
    private Long no;
    private Integer level;
    private Integer career;
    private Integer positionDetailNo;
    private Long memberNo;
}
