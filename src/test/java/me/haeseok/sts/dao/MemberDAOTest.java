package me.haeseok.sts.dao;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
class MemberDAOTest {
    private final MemberDAO memberDAO;

    MemberDTO memberDTO;

    @BeforeEach
    void before() {
        memberDTO = MemberDTO.builder()
                .email("test@test.com")
                .password("111test222")
                .nickname("테스트")
                .build();
    }

    @Test
    @DisplayName("단일 회원 추가")
    void addMember() {
        memberDAO.addMember(memberDTO);
        assertThat(memberDTO.getNickname()).isEqualTo("테스트");
    }

    @Test
    @DisplayName("단일 회원 조회")
    void readMember() {
        memberDAO.addMember(memberDTO);
        MemberDTO member = memberDAO.readMember(memberDTO.getNo());
        assertThat(member.getNickname()).isEqualTo("테스트");
    }

    @Test
    @DisplayName("단일 회원 제거")
    void dropMember() {
        memberDAO.addMember(memberDTO);
        memberDAO.dropMember(memberDTO.getNo());

        assertThat(memberDAO.readMember(memberDTO.getNo())).isNull();
    }
}