package me.haeseok.sts.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.MemberDAO;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberDAO memberDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        MemberDTO member = memberDAO.findMemberByEmail(userDetails.getUsername());

        // 로그인 정보 세션에 담기
        HttpSession session = request.getSession();
        session.setAttribute("MEMBERNO", member.getNo());
        session.setAttribute("MEMBEREMAIL", member.getEmail());
        session.setAttribute("MEMBERNICKNAME", member.getNickname());

        // 로그인 처리
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("type", "success");
        resultMap.put("redirect", "/");

        // 리다이렉트 경로 지정
        String redirectParam = request.getParameter("redirect");
        if( redirectParam!=null && !redirectParam.isBlank() ) {
            resultMap.put("redirect", redirectParam);
        }

        // Result
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().print(objectMapper.writeValueAsString(resultMap));
    }
}
