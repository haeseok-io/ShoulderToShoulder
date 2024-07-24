package me.haeseok.sts.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.*;
import me.haeseok.sts.dto.*;
import me.haeseok.sts.request.MoimListRequest;
import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MoimHeadcountResponse;
import me.haeseok.sts.response.MoimListResponse;
import me.haeseok.sts.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimServiceImple implements MoimService {
    private final FileUploadService fileUploadService;
    private final HttpSession session;
    private final MoimDAO moimDAO;
    private final MoimDetailDAO moimDetailDAO;
    private final MoimPlatformDAO moimPlatformDAO;
    private final MoimHeadcountDAO moimHeadcountDAO;
    private final MoimLanguageDAO moimLanguageDAO;
    private final MoimLinkDAO moimLinkDAO;
    private final MemberDAO memberDAO;
    private final CategoryDAO categoryDAO;
    private final StudyCategoryDAO studyCategoryDAO;
    private final PositionDAO positionDAO;
    private final PositionDetailDAO positionDetailDAO;
    private final MoimApplicantDAO moimApplicantDAO;


    @Value("${me.haeseok.sts.upload.directory.moim}")
    private String uploadDirectory;

    @Override
    public CustomPageResponse<MoimListResponse> readMoimList(MoimListRequest request) {
        Pageable pageable = request.getPageRequest().getPageable(request.getSortField(), request.getSortType());
        request.setStart(pageable.getOffset());
        request.setEnd(pageable.getPageSize());

        List<MoimDTO> moimList = moimDAO.findSearchList(request);
        List<MoimListResponse> moimResponseList = moimList.stream().map(data -> {
            Object category = data.getType().equals("P")
                    ? categoryDAO.findCategoryByMoinNo(data.getNo())
                    : studyCategoryDAO.getStudyCategoryByMoimNo(data.getNo());

            MoimListResponse moimListResponse = MoimListResponse.convertMoimDTO(data);
            moimListResponse.setWriter(memberDAO.findMemberByNo(data.getMemberNo()));
            moimListResponse.setCategory(category);
            moimListResponse.setLanguageList(moimLanguageDAO.getMoimLanguageByMoimNo(data.getNo()));
            moimListResponse.setHeadcountList(moimHeadcountDAO.getMoimHeadcountByMoimNo(data.getNo()).stream().map(headcount -> {
                PositionDetailDTO positionDetail = positionDetailDAO.getPositionDetailByNo(headcount.getPositionDetailNo());
                PositionDTO position = positionDAO.getPositionByNo(positionDetail.getPositionNo());

                return MoimHeadcountResponse.builder()
                        .no(headcount.getNo())
                        .position(position)
                        .positionDetail(positionDetail)
                        .personnelCount(headcount.getPersonnel())
                        .appliedCount(moimApplicantDAO.getAppliedCountByMoimHeadcountNo(headcount.getNo()))
                        .approvedCount(moimApplicantDAO.getApprovedCountByMoimHeadcountNo(headcount.getNo()))
                        .build();
            }).toList());

            return moimListResponse;
        }).toList();

        return CustomPageResponse.<MoimListResponse>pageBuilder()
                .request(request.getPageRequest())
                .dataList(moimResponseList)
                .total(moimDAO.getSearchTotal(request))
                .build();
    }

    @Override
    public Result addMoim(MoimWriteRequest request) {
        Long memberNo = (Long) session.getAttribute("MEMBERNO");
        String uploadFilename = null;

        try {
            if( !request.getThumbnail().isEmpty() ) {
                Map<String, String> fileUpload = fileUploadService.uploadFile(request.getThumbnail(), uploadDirectory);
                uploadFilename = fileUpload.get("uploadFileName");
            }

            Integer categoryNo = request.getType().equals("P") ? request.getCategoryNo() : request.getStudyCategoryNo();

            // 모임 등록
            MoimDTO moimDTO = MoimDTO.builder()
                    .type(request.getType())
                    .subject(request.getSubject())
                    .explanation(request.getExplanation())
                    .thumbnail(uploadFilename)
                    .memberNo(memberNo)
                    .build();
            moimDAO.addOne(moimDTO);

            // 모임 상세 등록
            MoimDetailDTO moimDetailDTO = MoimDetailDTO.builder()
                    .no(moimDTO.getNo())
                    .contents(request.getContents())
                    .price(request.getPrice())
                    .location(request.getLocation())
                    .onlineNo(request.getOnlineNo())
                    .categoryNo(request.getCategoryNo())
                    .studyCategoryNo(request.getStudyCategoryNo())
                    .build();
            moimDetailDAO.addOne(moimDetailDTO);

            // 모집인원 등록
            request.getHeadcountGroupList().forEach(headcountGroup -> moimHeadcountDAO.addOne(
                    MoimHeadcountDTO.builder()
                        .personnel(headcountGroup.getPersonnel())
                        .positionDetailNo(headcountGroup.getPositionDetailNo())
                        .moimNo(moimDTO.getNo())
                        .build()
            ));

            // 사용기술/언어 등록
            request.getLanguage().forEach(language -> moimLanguageDAO.addOne(
                    MoimLanguageDTO.builder()
                        .name(language)
                        .moimNo(moimDTO.getNo())
                        .build()
            ));

            // 출시 플랫폼 등록
            if( request.getPlatformNoList()!=null && !request.getPlatformNoList().isEmpty() ) {
                request.getPlatformNoList().forEach(platformNo -> moimPlatformDAO.addOne(
                        MoimPlatformDTO.builder()
                                .platformNo(platformNo)
                                .moimNo(moimDTO.getNo())
                                .build()
                ));
            }

            // 참고자료 등록
            if( request.getLinkGroupList()!=null && !request.getLinkGroupList().isEmpty() ) {
                request.getLinkGroupList().forEach(linkGroup -> moimLinkDAO.addOne(
                        MoimLinkDTO.builder()
                                .url(linkGroup.getUrl())
                                .moimNo(moimDTO.getNo())
                                .build()
                ));
            }

        } catch(IOException e) {
            return Result.failure(e.getMessage());
        }

        return Result.success("모임이 개설되었습니다.");
    }
}
