package me.haeseok.sts.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moim")
public class MoimController {
    @GetMapping("/")
    public String moimList() {

        return "moim/list";
    }
}
