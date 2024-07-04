package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MoimDetailDAO {
    void addOne(MoimDetailDTO dto);
}
