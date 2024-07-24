package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.MemberDAO;
import me.haeseok.sts.dao.MemberDetailDAO;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.dto.MemberDetailDTO;
import me.haeseok.sts.request.MemberListRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MemberListResponse;
import me.haeseok.sts.response.MoimListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImple implements MemberService {
    private final PasswordEncoder bCryptPasswordEncoder;
    private final MemberDAO memberDAO;
    private final MemberDetailDAO memberDetailDAO;

    @Override
    public MemberDTO register(MemberDTO memberDTO) {
        memberDTO.hashPassword(bCryptPasswordEncoder);
        memberDTO.setRole("USER");

        memberDAO.addMember(memberDTO);
        
        return memberDTO;
    }

    @Override
    public CustomPageResponse<MemberListResponse> readMemberList(MemberListRequest request) {
        // Page
        Pageable pageable = request.getPageRequest().getPageable(request.getSortField(), request.getSortType());
        request.setStart(pageable.getOffset());
        request.setEnd(pageable.getPageSize());

        // Data
        List<MemberListResponse> memberResponseList = memberDAO.findSearchList(request).stream().map(data -> {
            MemberDetailDTO memberDetailDTO = memberDetailDAO.findMemberDetailByNo(data.getNo());
            String profileImg = memberDetailDTO!=null ? memberDetailDTO.getProfileImg() : null;

            MemberListResponse memberListResponse = MemberListResponse.convertMemberDTO(data);
            memberListResponse.setProfileImg(profileImg);


            return memberListResponse;
        }).toList();

        return CustomPageResponse.<MemberListResponse>pageBuilder()
                .request(request.getPageRequest())
                .dataList(memberResponseList)
                .total(memberDAO.getSearchTotal(request))
                .build();
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
