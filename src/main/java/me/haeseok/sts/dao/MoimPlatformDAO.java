package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimPlatformDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimPlatformDAO {
    void addOne(MoimPlatformDTO dto);
}
