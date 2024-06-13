package me.haeseok.sts.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    // 로그인 여부 확인
    public static boolean isLoggedIn(HttpSession session) {
        String memberEmail = (String) session.getAttribute("MEMBEREMAIL");
        return memberEmail!=null && !memberEmail.isBlank();
    }
}
