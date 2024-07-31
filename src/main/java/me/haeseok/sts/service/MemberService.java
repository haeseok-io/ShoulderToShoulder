package me.haeseok.sts.service;

import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.request.MemberListRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MemberListResponse;
import me.haeseok.sts.response.MemberResponse;

public interface MemberService {
    MemberDTO register(MemberDTO memberDTO);
    MemberResponse readMember(Long memberNo);
    CustomPageResponse<MemberListResponse> readMemberList(MemberListRequest request);
    MemberListResponse readTodayMember();
    boolean isEmailExist(String email);
    boolean isNicknameExist(String nickname);
    MemberListResponse convertMemberList(MemberDTO memberDTO);
}
