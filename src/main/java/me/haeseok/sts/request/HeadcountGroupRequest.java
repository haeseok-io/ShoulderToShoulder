package me.haeseok.sts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeadcountGroupRequest {
    private Integer no;
    private Integer positionNo;
    private Integer positionDetailNo;
    private Integer personnel;
}
