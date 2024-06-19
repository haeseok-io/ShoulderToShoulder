package me.haeseok.sts.service;

import me.haeseok.sts.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> readAll();
}
