package me.haeseok.sts.dao;

import me.haeseok.sts.dto.StudyCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudyCategoryDAO {
    List<StudyCategoryDTO> getStudyCategoryList();
}
