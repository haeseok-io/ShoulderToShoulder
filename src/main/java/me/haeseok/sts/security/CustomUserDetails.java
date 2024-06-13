package me.haeseok.sts.security;

import me.haeseok.sts.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final MemberDTO memberDTO;

    public CustomUserDetails(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    /**
     *  # 계정 만료 여부
     *  @return
     *      true    : 만료되지 않음
     *      false   : 만료됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *  # 계정 잠김 여부
     *  @return
     *      true    : 잠기지 않음
     *      false   : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *  # 비밀번호 만료 여부
     *  @return
     *      true    : 만료되지 않음
     *      false   : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *  # 사용자 활성 여부
     *  @return
     *      true    : 활성화
     *      false   : 비활성화
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberDTO.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return memberDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDTO.getEmail();
    }
}
