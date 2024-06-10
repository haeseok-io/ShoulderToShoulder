package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerOk(MemberDTO memberDTO) {
        // 필수값 체크
        if( memberDTO.getEmail()==null || memberDTO.getEmail().isBlank() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일은 필수 입력 항목입니다.");
        } else if( memberDTO.getPassword()==null || memberDTO.getPassword().isBlank() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호는 필수 입력 항목입니다.");
        } else if( memberDTO.getNickname()==null || memberDTO.getNickname().isBlank() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("닉네임은 필수 입력 항목입니다.");
        }

        // 회원가입 처리
        MemberDTO member = memberService.register(memberDTO);

        return null;
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
