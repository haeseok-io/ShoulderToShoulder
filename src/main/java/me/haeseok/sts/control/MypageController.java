package me.haeseok.sts.control;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.service.CategoryService;
import me.haeseok.sts.service.MemberService;
import me.haeseok.sts.service.OnlineService;
import me.haeseok.sts.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MemberService memberService;
    private final PositionService positionService;
    private final OnlineService onlineService;
    private final CategoryService categoryService;

    @GetMapping(value = {"/", "/user"})
    public String user(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("MEMBERNO");

        model.addAttribute("data", memberService.readMember(memberNo));
        model.addAttribute("positionList", positionService.readPositionList());
        model.addAttribute("onlineList", onlineService.readOnlineList());
        model.addAttribute("categoryList", categoryService.readCategoryAll());

        return "mypage/user";
    }
}
