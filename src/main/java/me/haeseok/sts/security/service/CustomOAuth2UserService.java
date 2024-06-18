package me.haeseok.sts.security.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.MemberDAO;
import me.haeseok.sts.dto.MemberDTO;
import me.haeseok.sts.security.CustomOAuth2User;
import me.haeseok.sts.security.dto.KakaoResponse;
import me.haeseok.sts.security.dto.NaverResponse;
import me.haeseok.sts.security.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberDAO memberDAO;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String serviceProvider = userRequest.getClientRegistration().getRegistrationId();

        // 소셜 로그인 응답
        OAuth2Response oAuth2Response = null;
        if( serviceProvider.equals("kakao") ) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else if( serviceProvider.equals("naver") ) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        // 회원 조회
        MemberDTO member = memberDAO.findMemberEmailAsProvider(oAuth2Response.getEmail(), serviceProvider);

        // 조회되는 회원이 없을 경우 가입 처리
        if( member==null ) {
            member = MemberDTO.builder()
                    .email(oAuth2Response.getEmail())
                    .nickname(oAuth2Response.getNickname()+"_"+oAuth2Response.getProviderId())
                    .provider(serviceProvider)
                    .build();

            memberDAO.addMember(member);
        }

        return new CustomOAuth2User(oAuth2Response, "user", member.getNo());
    }
}
