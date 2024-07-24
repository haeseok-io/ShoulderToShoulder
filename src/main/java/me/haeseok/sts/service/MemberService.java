package me.haeseok.sts.service;

import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.request.MemberListRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MemberListResponse;

public interface MemberService {
    MemberDTO register(MemberDTO memberDTO);
    CustomPageResponse<MemberListResponse> readMemberList(MemberListRequest request);
    boolean isEmailExist(String email);
    boolean isNicknameExist(String nickname);
}
