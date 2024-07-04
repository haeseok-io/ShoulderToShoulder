package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimDAO {
    void addOne(MoimDTO dto);
}
