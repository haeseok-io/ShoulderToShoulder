package me.haeseok.sts.service;

import me.haeseok.sts.dto.MemberDTO;

public interface MemberService {
    MemberDTO register(MemberDTO memberDTO);
    boolean isEmailExist(String email);
    boolean isNicknameExist(String nickname);
}
