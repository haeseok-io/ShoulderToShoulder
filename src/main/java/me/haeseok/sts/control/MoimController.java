package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moim")
@RequiredArgsConstructor
public class MoimController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public String moimList() {
        return "moim/list";
    }

    @GetMapping("/write")
    public String write0(Model model) {
        model.addAttribute("categoryList", categoryService.readAll());

        return "moim/write";
    }
}
