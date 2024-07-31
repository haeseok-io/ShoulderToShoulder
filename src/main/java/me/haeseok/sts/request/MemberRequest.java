package me.haeseok.sts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.MemberPortfolioDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private Long no;
    private String nickname;
    private MultipartFile profileImg;
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
