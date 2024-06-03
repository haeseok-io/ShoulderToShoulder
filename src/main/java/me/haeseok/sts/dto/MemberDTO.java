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
public class MemberDTO {
    private Long no;
    private String email;
    private String password;
    private String nickname;
    private String introduce;
    private String preferArea;
    private String profileImg;
    private String gitLink;
    private String blogLink;
    private Timestamp regdate;
    private String role;
}
