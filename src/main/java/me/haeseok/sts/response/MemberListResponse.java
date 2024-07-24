package me.haeseok.sts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberListResponse {
    private Long no;
    private String email;
    private String nickname;
    private Timestamp regdate;
    private String profileImg;
    private Integer ongoingMoimCount;

    private MemberJobDTO job;
    private CategoryDTO category;
    private PositionDTO position;
    private PositionDetailDTO positionDetail;

    public static MemberListResponse convertMemberDTO(MemberDTO memberDTO) {
        return MemberListResponse.builder()
                .no(memberDTO.getNo())
                .email(memberDTO.getEmail())
                .nickname(memberDTO.getNickname())
                .regdate(memberDTO.getRegdate())
                .build();
    }
}
