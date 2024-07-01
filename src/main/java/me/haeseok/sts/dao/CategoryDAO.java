package me.haeseok.sts.dao;

import me.haeseok.sts.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryDAO {
    List<CategoryDTO> getCategoryList();
}
