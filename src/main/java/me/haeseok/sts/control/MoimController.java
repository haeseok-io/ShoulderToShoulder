package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.service.CategoryService;
import me.haeseok.sts.service.OnlineService;
import me.haeseok.sts.service.PlatformService;
import me.haeseok.sts.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {
    private final CategoryService categoryService;
    private final PlatformService platformService;
    private final OnlineService onlineService;
    private final PositionService positionService;

    @GetMapping("/")
    public String moimList() {
        return "moim/list";
    }

    @GetMapping("/write")
    public String write0(Model model) {
        model.addAttribute("categoryList", categoryService.readCategoryList());
        model.addAttribute("platformList", platformService.readPlatformList());
        model.addAttribute("onlineList", onlineService.readOnlineList());
        model.addAttribute("positionList", positionService.readPositionList());

        return "moim/write";
    }
}
