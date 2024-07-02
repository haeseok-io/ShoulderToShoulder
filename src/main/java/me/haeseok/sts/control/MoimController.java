package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {
    private final CategoryService categoryService;
    private final StudyCategoryService studyCategoryService;
    private final PlatformService platformService;
    private final OnlineService onlineService;
    private final PositionService positionService;

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
    public String writeProcess(@ModelAttribute MoimWriteRequest request) {
        System.out.println(request);
        return "redirect:/";
    }
}
