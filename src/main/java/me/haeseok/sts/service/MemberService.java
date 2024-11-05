package me.haeseok.sts.service;

import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.request.MemberListRequest;
import me.haeseok.sts.request.MemberRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MemberListResponse;
import me.haeseok.sts.response.MemberResponse;
import me.haeseok.sts.util.Result;

public interface MemberService {
    MemberDTO register(MemberDTO memberDTO);
    Result modify(MemberRequest request);
    MemberResponse readMember(Long memberNo);
    CustomPageResponse<MemberListResponse> readMemberList(MemberListRequest request);
    MemberListResponse readTodayMember();
    boolean isEmailExist(String email);
    boolean isNicknameExist(String nickname);
    MemberListResponse convertMemberList(MemberDTO memberDTO);
}
