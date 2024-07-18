package me.haeseok.sts.dao;

import me.haeseok.sts.dto.MoimDTO;
import me.haeseok.sts.request.MoimListRequest;
import me.haeseok.sts.response.MoimListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MoimDAO {
    Long getTotal();
    Long getSearchTotal(MoimListRequest request);
    List<MoimDTO> findSearchList(MoimListRequest request);

    void addOne(MoimDTO dto);
}
