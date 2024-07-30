package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    @GetMapping(value = {"/", "/user"})
    public String user() {
        return "mypage/user";
    }
}
