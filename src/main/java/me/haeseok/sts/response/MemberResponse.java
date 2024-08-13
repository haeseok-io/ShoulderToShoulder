package me.haeseok.sts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.MemberPortfolioDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long no;
    private String email;
    private String nickname;
    private String profileImg;
    private String introduce;
    private Integer position;
    private Integer positionDetail;
    private Integer level;
    private Integer career;
    private Integer online;
    private String preferArea;
    private String gitLink;
    private String blogLink;
    private List<Integer> category;
    private List<MemberPortfolioDTO> portfolio;
}
