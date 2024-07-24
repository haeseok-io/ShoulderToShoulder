package me.haeseok.sts.security.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.MemberDAO;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberDAO memberDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDTO member = memberDAO.findMemberByEmail(email);

        if( member==null ) {
            throw new UsernameNotFoundException("조회되는 이메일이 없습니다.");
        }

        return new CustomUserDetails(member);
    }
}
