package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.CategoryDAO;
import me.haeseok.sts.dto.CategoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImple implements CategoryService {
    private final CategoryDAO categoryDAO;

    @Override
    public List<CategoryDTO> readCategoryList() {
        return categoryDAO.getCategoryList();
    }
}
