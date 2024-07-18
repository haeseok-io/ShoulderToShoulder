package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimApplicantDTO {
    private Long no;
    private String applicantStatus;
    private String approvalStatus;
    private String rejectionReason;
    private Long memberNo;
    private Long moimHeadcountNo;
}
