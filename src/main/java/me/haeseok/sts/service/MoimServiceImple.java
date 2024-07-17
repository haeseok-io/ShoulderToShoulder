package me.haeseok.sts.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.*;
import me.haeseok.sts.dto.*;
import me.haeseok.sts.request.MoimListRequest;
import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.response.CustomPageResponse;
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
    private final HttpSession session;
    private final MoimDAO moimDAO;
    private final MoimDetailDAO moimDetailDAO;
    private final MoimPlatformDAO moimPlatformDAO;
    private final MoimHeadcountDAO moimHeadcountDAO;
    private final MoimLanguageDAO moimLanguageDAO;
    private final MoimLinkDAO moimLinkDAO;
    private final FileUploadService fileUploadService;
    private final MemberDAO memberDAO;

    @Value("${me.haeseok.sts.upload.directory.moim}")
    private String uploadDirectory;

    @Override
    public CustomPageResponse<MoimListResponse> readMoimList(MoimListRequest request) {
        int total = moimDAO.readSearchTotal(request);

        Pageable pageable = request.getPageRequest().getPageable(request.getSortField(), request.getSortType());
        request.setStart(pageable.getOffset());
        request.setEnd(pageable.getPageSize());

        List<MoimDTO> moimList = moimDAO.readSearchList(request);

        List<MoimListResponse> moimResponseList = moimList.stream().map(data -> {
            MoimListResponse moimListResponse = MoimListResponse.convertMoimDTO(data);

            System.out.println(data );
            moimListResponse.setWriter(memberDAO.findMemberNo(data.getMemberNo()));
            //moimListResponse.setCategory();
            //moimListResponse.setLanguageList();
            //moimListResponse.setHeadcountList();

            return moimListResponse;
        }).toList();


        System.out.println(moimResponseList);
        //CustomPageResponse<MoimListResponse


        return null;
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
                .categoryNo(categoryNo)
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
