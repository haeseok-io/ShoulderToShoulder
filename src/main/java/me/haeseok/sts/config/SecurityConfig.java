package me.haeseok.sts.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.haeseok.sts.security.handler.CustomAuthenticationFailureHandler;
import me.haeseok.sts.security.handler.CustomAuthenticationSuccessHandler;
import me.haeseok.sts.security.handler.CustomOAuth2AuthenticationSuccessHandler;
import me.haeseok.sts.security.service.CustomOAuth2UserService;
import me.haeseok.sts.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("# 시큐리티 적용중 #");

        http
                .csrf((csrfConfig) -> csrfConfig.disable())
                .authenticationProvider(daoAuthenticationProvider())
                // 권한 설정
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                .requestMatchers("/moim/write").hasAnyRole("USER") // user 권한이 있는 사용자만 접근 가능
                                .anyRequest().permitAll()
                )
                .formLogin(auth ->
                        auth
                                .loginPage("/login")
                                .loginProcessingUrl("/login/loginProc")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .successHandler(customAuthenticationSuccessHandler)
                                .failureHandler(customAuthenticationFailureHandler)
                )
                .oauth2Login(oauth2 ->
                        oauth2
                                .loginPage("/login")
                                .userInfoEndpoint(user ->
                                    user.userService(customOAuth2UserService)
                                )
                                .successHandler(customOAuth2AuthenticationSuccessHandler)
                                .failureUrl("/")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
