package me.haeseok.sts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberListRequest {
    private Integer positionDetailNo;
    private Integer categoryNo;
    private String keyword;

    private CustomPageRequest pageRequest;

    @Builder.Default
    private Long start = 0L;
    @Builder.Default
    private Integer end = 10;
    @Builder.Default
    private String sortField = "no";
    @Builder.Default
    private String sortType = "desc";
}
