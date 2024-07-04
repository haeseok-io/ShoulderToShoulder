package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimDTO {
    private Long no;
    private String type;
    private String subject;
    private String explanation;
    private String thumbnail;
    private String status;
    private int like;
    private int hits;
    private Timestamp regdate;
    private Long memberNo;
}
