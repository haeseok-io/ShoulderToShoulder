package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDTO {
    private Long no;
    private String introduce;
    private String preferArea;
    private String profileImg;
    private String gitLink;
    private String blogLink;
}
