package me.haeseok.sts.security;

import me.haeseok.sts.security.dto.OAuth2Response;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2Response oAuth2Response;
    private final String role;
    private final Long memberNo;

    public CustomOAuth2User(OAuth2Response oAuth2Response, String role, Long memberNo) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
        this.memberNo = memberNo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Response.getNickname();
    }

    public Long getNo() {
        return this.memberNo;
    }
}
