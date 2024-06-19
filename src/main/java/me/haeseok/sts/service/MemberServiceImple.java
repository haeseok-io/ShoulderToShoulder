package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.MemberDAO;
import me.haeseok.sts.dto.MemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImple implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public MemberDTO register(MemberDTO memberDTO) {
        memberDTO.hashPassword(bCryptPasswordEncoder);
        memberDTO.setRole("USER");

        memberDAO.addMember(memberDTO);
        
        return memberDTO;
    }

    /**
     * # 이메일 중복 체크
     *
     * @Param email 이메일
     * @Return true | false
     */
    @Override
    public boolean isEmailExist(String email) {
        return memberDAO.emailExist(email);
    }

    /**
     * # 닉네임 중복 체크
     *
     * @Param nickname 닉네임
     * @Return true | false
     */
    @Override
    public boolean isNicknameExist(String nickname) {
        return memberDAO.nicknameExist(nickname);
    }
}
