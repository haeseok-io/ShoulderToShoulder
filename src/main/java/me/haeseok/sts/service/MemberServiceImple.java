package me.haeseok.sts.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.*;
import me.haeseok.sts.dto.*;
import me.haeseok.sts.request.MemberListRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MemberListResponse;
import me.haeseok.sts.response.MemberResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImple implements MemberService {
    private final PasswordEncoder bCryptPasswordEncoder;
    private final MemberDAO memberDAO;
    private final MemberDetailDAO memberDetailDAO;
    private final MemberJobDAO memberJobDAO;
    private final MemberOnlineDAO memberOnlineDAO;
    private final MemberCategoryDAO memberCategoryDAO;
    private final MemberPortfolioDAO memberPortfolioDAO;
    private final TodayMemberDAO todayMemberDAO;
    private final PositionDetailDAO positionDetailDAO;

    @Override
    public MemberDTO register(MemberDTO memberDTO) {
        memberDTO.hashPassword(bCryptPasswordEncoder);
        memberDTO.setRole("USER");

        memberDAO.addMember(memberDTO);
        
        return memberDTO;
    }

    @Override
    public MemberResponse readMember(Long memberNo) {
        MemberResponse response = new MemberResponse();
        MemberDTO member = memberDAO.findMemberByNo(memberNo);
        Optional<MemberDetailDTO> memberDetail = Optional.ofNullable(memberDetailDAO.findMemberDetailByNo(memberNo));
        Optional<MemberJobDTO> memberJob = Optional.ofNullable(memberJobDAO.findMemberJobByMemberNo(memberNo));
        Optional<MemberOnlineDTO> memberOnline = Optional.ofNullable(memberOnlineDAO.findMemberOnlineByMemberNo(memberNo));
        Optional<List<MemberCategoryDTO>> memberCategory = Optional.ofNullable(memberCategoryDAO.findMemberCategoryByMemberNo(memberNo));

        Optional<PositionDetailDTO> positionDetail = memberJob.map(MemberJobDTO::getPositionDetailNo).flatMap(positionDetailNo -> Optional.ofNullable(positionDetailDAO.getPositionDetailByNo(positionDetailNo)));


        response.setNo(member.getNo());
        response.setNickname(member.getNickname());
        response.setProfileImg(memberDetail.map(MemberDetailDTO::getProfileImg).orElse(null));
        response.setIntroduce(memberDetail.map(MemberDetailDTO::getIntroduce).orElse(null));
        response.setPreferArea(memberDetail.map(MemberDetailDTO::getPreferArea).orElse(null));
        response.setGitLink(memberDetail.map(MemberDetailDTO::getGitLink).orElse(null));
        response.setBlogLink(memberDetail.map(MemberDetailDTO::getBlogLink).orElse(null));
        response.setLevel(memberJob.map(MemberJobDTO::getLevel).orElse(null));
        response.setCareer(memberJob.map(MemberJobDTO::getCareer).orElse(null));
        response.setPosition(positionDetail.map(PositionDetailDTO::getPositionNo).orElse(null));
        response.setPositionDetail(memberJob.map(MemberJobDTO::getPositionDetailNo).orElse(null));
        response.setOnline(memberOnline.map(MemberOnlineDTO::getOnlineNo).orElse(null));
        response.setCategory(memberCategory.orElse(Collections.emptyList()).stream().map(MemberCategoryDTO::getCategoryNo).toList());
        response.setPortfolio(memberPortfolioDAO.findMemberPortfolioByMemberNo(memberNo));

        return response;
    }

    @Override
    public CustomPageResponse<MemberListResponse> readMemberList(MemberListRequest request) {
        // Page
        Pageable pageable = request.getPageRequest().getPageable(request.getSortField(), request.getSortType());
        request.setStart(pageable.getOffset());
        request.setEnd(pageable.getPageSize());

        // Data
        List<MemberListResponse> memberResponseList = memberDAO.findSearchList(request).stream().map(this::convertMemberList).toList();
        return CustomPageResponse.<MemberListResponse>pageBuilder()
                .request(request.getPageRequest())
                .dataList(memberResponseList)
                .total(memberDAO.getSearchTotal(request))
                .build();
    }

    @Override
    public MemberListResponse readTodayMember() {
        LocalDate today = LocalDate.now();
        TodayMemberDTO todayMember = todayMemberDAO.findTodayMemberByDate(today);
        MemberDTO randomMember = null;

        if( todayMember==null ) {
            randomMember = memberDAO.getMemberRandom();
            todayMemberDAO.addTodayMember(TodayMemberDTO.builder()
                            .memberNo(randomMember.getNo())
                            .regdate(today)
                            .build());
        } else {
            randomMember = memberDAO.findMemberByNo(todayMember.getMemberNo());
        }

        return convertMemberList(randomMember);
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

    @Override
    public MemberListResponse convertMemberList(MemberDTO memberDTO) {
        MemberDetailDTO memberDetailDTO = memberDetailDAO.findMemberDetailByNo(memberDTO.getNo());
        String profileImg = memberDetailDTO!=null ? memberDetailDTO.getProfileImg() : null;

        MemberListResponse memberListResponse = MemberListResponse.convertMemberDTO(memberDTO);
        memberListResponse.setProfileImg(profileImg);
        //memberListResponse.setJob();
        return memberListResponse;
    }
}
