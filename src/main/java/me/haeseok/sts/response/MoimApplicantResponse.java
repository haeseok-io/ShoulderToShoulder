package me.haeseok.sts.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.haeseok.sts.dto.MemberDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimApplicantResponse {
    private Long no;
    private String reason;
    private String status;
    private MemberDTO member;
}
