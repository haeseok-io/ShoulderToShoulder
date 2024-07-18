package me.haeseok.sts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.dto.MoimDTO;
import me.haeseok.sts.dto.MoimHeadcountDTO;
import me.haeseok.sts.dto.MoimLanguageDTO;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimListResponse {
    private Long no;
    private String type;
    private String subject;
    private String explanation;
    private String thumbnail;
    private String status;
    private int like;
    private int hits;
    private Timestamp regdate;

    private MemberDTO writer;
    private Object category;
    private List<MoimLanguageDTO> languageList;
    private List<MoimHeadcountResponse> headcountList;

    public static MoimListResponse convertMoimDTO(MoimDTO moimDTO) {
        return MoimListResponse.builder()
                .no(moimDTO.getNo())
                .type(moimDTO.getType())
                .subject(moimDTO.getSubject())
                .explanation(moimDTO.getExplanation())
                .thumbnail(moimDTO.getThumbnail())
                .status(moimDTO.getStatus())
                .like(moimDTO.getLike())
                .hits(moimDTO.getHits())
                .regdate(moimDTO.getRegdate())
                .build();
    }
}
