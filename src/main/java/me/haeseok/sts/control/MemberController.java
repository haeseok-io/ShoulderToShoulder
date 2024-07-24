package me.haeseok.sts.control;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.request.MemberListRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MemberListResponse;
import me.haeseok.sts.service.MemberService;
import me.haeseok.sts.util.SessionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        // 로그인이 되어있을 경우 메인페이지로 이동
        if( SessionUtils.isLoggedIn(session) ) {
            return "redirect:/";
        }

        return "login";
    }

    @GetMapping("/register")
    public String register(HttpSession session) {
        // 로그인이 되어있을 경우 메인페이지로 이동
        if( SessionUtils.isLoggedIn(session) ) {
            return "redirect:/";
        }

        return "register";
    }

    @ResponseBody
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomPageResponse<MemberListResponse>> memberList(MemberListRequest request) {
        return ResponseEntity.ok().body(memberService.readMemberList(request));
    }

    @ResponseBody
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> registerOk(MemberDTO memberDTO) {
        Map<String, String> resultMap = new HashMap<>();

        // 필수값 체크
        if( memberDTO.getEmail()==null || memberDTO.getEmail().isBlank() ) {
            resultMap.put("type", "error");
            resultMap.put("message", "이메일은 필수 입력 항목입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
        } else if( memberDTO.getPassword()==null || memberDTO.getPassword().isBlank() ) {
            resultMap.put("type", "error");
            resultMap.put("message", "비밀번호는 필수 입력 항목입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
        } else if( memberDTO.getNickname()==null || memberDTO.getNickname().isBlank() ) {
            resultMap.put("type", "error");
            resultMap.put("message", "닉네임은 필수 입력 항목입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
        }

        // 회원가입 처리
        MemberDTO member = memberService.register(memberDTO);

        resultMap.put("type", "success");
        resultMap.put("message", "회원가입이 완료 되었습니다.");
        return ResponseEntity.ok().body(resultMap);
    }

    @ResponseBody
    @GetMapping(value = "/emailExist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> emailExist(@RequestParam("email") String email) {
        Map<String, String> resultMap = new HashMap<>();
        
        if( memberService.isEmailExist(email) ) { // 이메일 중복 체크
            resultMap.put("type", "error");
            resultMap.put("message", "이미 사용중인 이메일 입니다.");
        } else {
            resultMap.put("type", "success");
            resultMap.put("message", "사용 가능한 이메일 입니다.");
        }

        return ResponseEntity.ok().body(resultMap);
    }

    @ResponseBody
    @GetMapping(value = "/nicknameExist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> nicknameExist(@RequestParam("nickname") String nickname) {
        Map<String, String> resultMap = new HashMap<>();

        if( memberService.isNicknameExist(nickname) ) {
            resultMap.put("type", "error");
            resultMap.put("message", "이미 사용중인 닉네임 입니다.");
        } else {
            resultMap.put("type", "success");
            resultMap.put("message", "사용 가능한 닉네임 입니다.");
        }

        return ResponseEntity.ok().body(resultMap);
    }
}
