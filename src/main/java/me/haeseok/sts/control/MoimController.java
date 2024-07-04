package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.service.*;
import me.haeseok.sts.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {
    private final CategoryService categoryService;
    private final StudyCategoryService studyCategoryService;
    private final PlatformService platformService;
    private final OnlineService onlineService;
    private final PositionService positionService;

    private final MoimService moimService;

    @GetMapping("/")
    public String moimList() {
        return "moim/list";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categoryList", categoryService.readCategoryList());
        model.addAttribute("studyCategoryList", studyCategoryService.readStudyCategoryList());
        model.addAttribute("platformList", platformService.readPlatformList());
        model.addAttribute("onlineList", onlineService.readOnlineList());
        model.addAttribute("positionList", positionService.readPositionList());

        return "moim/write";
    }

    @PostMapping("/write")
    public String writeProcess(@ModelAttribute MoimWriteRequest request, RedirectAttributes redirectAttributes) {

        // Check
        if( request.getSubject()==null || request.getSubject().isBlank() ) {
            return errorMessage(redirectAttributes, "프로젝트명이 존재하지 않습니다.", "/moim/write");
        } else if( request.getHeadcountGroupList()==null ) {
            return errorMessage(redirectAttributes, "모집인원이 존재하지 않습니다.", "/moim/write");
        } else if( request.getLanguage()==null ) {
            return errorMessage(redirectAttributes, "사용 언어/기술이 존재하지 않습니다.", "/moim/write");
        }
        // TODO::상세설명 조건 추가해야함 ( 프론트 작업 완료 후 )

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
