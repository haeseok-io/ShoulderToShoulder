package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimLinkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimLinkDAO {
    void addOne(MoimLinkDTO dto);
}
