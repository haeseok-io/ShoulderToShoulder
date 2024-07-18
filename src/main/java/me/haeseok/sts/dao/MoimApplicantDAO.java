package me.haeseok.sts.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimApplicantDAO {
    // 지원자 수 가져오기
    int getAppliedCountByMoimHeadcountNo(Long headcountNo);
    // 승인자 수 가져오기
    int getApprovedCountByMoimHeadcountNo(Long headcountNo);
}
