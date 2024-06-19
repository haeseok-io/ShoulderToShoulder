package me.haeseok.sts.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");

        StringBuffer errorMsg = new StringBuffer();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> resultMap = new HashMap<>();

        // Check
        if( emailParam==null || emailParam.isBlank() ) {
            errorMsg.append("이메일 정보가 존재하지 않습니다.");
        } else if( passwordParam==null || passwordParam.isBlank() ) {
            errorMsg.append("비밀번호 정보가 존재하지 않습니다.");
        } else if ( exception instanceof UsernameNotFoundException ) {
            errorMsg.append(exception.getMessage());
        } else if( exception.getMessage().equals("Bad credentials") ) {
            errorMsg.append("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 실패 처리
        resultMap.put("type", "error");
        resultMap.put("message", errorMsg.toString());

        // Result
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().print(objectMapper.writeValueAsString(resultMap));
    }
}
