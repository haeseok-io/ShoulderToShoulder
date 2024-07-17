package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimDTO;
import me.haeseok.sts.request.MoimListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MoimDAO {
    void addOne(MoimDTO dto);
    int readSearchTotal(MoimListRequest request);
    List<MoimDTO> readSearchList(MoimListRequest request);
}
