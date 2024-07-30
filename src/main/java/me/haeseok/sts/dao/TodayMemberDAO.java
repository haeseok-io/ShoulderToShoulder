package me.haeseok.sts.dao;

import me.haeseok.sts.dto.TodayMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Mapper
@Repository
public interface TodayMemberDAO {
    TodayMemberDTO findTodayMemberByDate(LocalDate today);
    void addTodayMember(TodayMemberDTO todayMemberDTO);
}
