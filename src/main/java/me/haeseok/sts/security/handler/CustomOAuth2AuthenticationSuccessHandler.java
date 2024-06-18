package me.haeseok.sts.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.MemberDAO;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.security.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberDAO memberDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        MemberDTO member = memberDAO.findMemberNo(customOAuth2User.getNo());

        // 로그인 정보 세션에 담기
        HttpSession session = request.getSession();
        session.setAttribute("MEMBERNO", member.getNo());
        session.setAttribute("MEMBEREMAIL", member.getEmail());
        session.setAttribute("MEMBERNICKNAME", member.getNickname());

        // 리다이렉트 경로 지정

        // Result
        response.sendRedirect("/");
    }
}
