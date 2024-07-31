package me.haeseok.sts.control;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.response.MemberResponse;
import me.haeseok.sts.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MemberService memberService;

    @GetMapping(value = {"/", "/user"})
    public String user(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("MEMBERNO");

        model.addAttribute("data", memberService.readMember(memberNo));

        return "mypage/user";
    }
}
