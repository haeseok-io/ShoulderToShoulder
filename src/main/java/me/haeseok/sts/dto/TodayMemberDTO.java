package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodayMemberDTO {
    private Long no;
    private Long memberNo;
    private LocalDate regdate;
}
