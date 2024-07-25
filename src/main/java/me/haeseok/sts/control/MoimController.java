package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.request.MoimListRequest;
import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MoimListResponse;
import me.haeseok.sts.service.*;
import me.haeseok.sts.util.Result;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/moim")
public class MoimController {
    private final MoimService moimService;
    private final CategoryService categoryService;
    private final StudyCategoryService studyCategoryService;
    private final PlatformService platformService;
    private final OnlineService onlineService;
    private final PositionService positionService;

    // String 값이 빈값으로 들어올 경우 null 로 변환
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String moimList(Model model) {
        model.addAttribute("positionMergeList", positionService.readPositionMergeList());
        return "moim/list";
    }

    @ResponseBody
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomPageResponse<MoimListResponse>> moimListData(MoimListRequest request) {
        return ResponseEntity.ok().body(moimService.readMoimList(request));
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categoryList", categoryService.readCategoryAll());
        model.addAttribute("studyCategoryList", studyCategoryService.readStudyCategoryAll());
        model.addAttribute("platformList", platformService.readPlatformList());
        model.addAttribute("onlineList", onlineService.readOnlineList());
        model.addAttribute("positionList", positionService.readPositionList());

        return "moim/write";
    }

    @PostMapping("/write")
    public String writeProcess(@ModelAttribute MoimWriteRequest request, RedirectAttributes redirectAttributes) {

        // 참고링크 url 이 null 일 경우 리스트 null 처리
        request.filterNullLinkGroup();

        // Check
        if( request.getSubject()==null || request.getSubject().isBlank() ) {
            return errorMessage(redirectAttributes, "모임명이 존재하지 않습니다.", "/moim/write");
        } else if( request.getHeadcountGroupList()==null ) {
            return errorMessage(redirectAttributes, "모집인원이 존재하지 않습니다.", "/moim/write");
        } else if( request.getLanguage()==null ) {
            return errorMessage(redirectAttributes, "사용 언어/기술이 존재하지 않습니다.", "/moim/write");
        } else if( request.getContents().isBlank() ) {
            return errorMessage(redirectAttributes, "설명이 존재하지 않습니다.", "/moim/write");
        }

        if( !request.getOnlineNo().equals(2) ) {
            if( request.getLocation()==null || request.getLocation().isBlank() ) {
                return errorMessage(redirectAttributes, "모임 장소가 존재하지 않습니다.", "/moim/write");
            } else if( request.getPrice()==null ) {
                return errorMessage(redirectAttributes, "오프라인 비용이 존재하지 않습니다.", "/moim/write");
            }
        }

        if( request.getType().equals("P") ) {
            if( request.getCategoryNo()==null ) {
                return errorMessage(redirectAttributes, "프로젝트 분야가 존재하지 않습니다.", "/moim/write");
            } else if( request.getPlatformNoList()==null ) {
                return errorMessage(redirectAttributes, "출시 플랫폼이 존재하지 않습니다.", "/moim/write");
            }
        }

        if( request.getType().equals("S") ) {
            if( request.getStudyCategoryNo()==null ) {
                return errorMessage(redirectAttributes, "스터디 분야가 존재하지 않습니다.", "/moim/write");
            }
        }

        // Process
        Result result = moimService.addMoim(request);
        if( !result.isSuccess() ) {
            return errorMessage(redirectAttributes, result.getMessage(), "/moim/write");
        }

        // Result
        return "redirect:/";
    }


    private String errorMessage(RedirectAttributes redirectAttributes, String errorMsg, String redirectPath) {
        redirectAttributes.addFlashAttribute("error", errorMsg);
        return "redirect:"+redirectPath;
    }
}
