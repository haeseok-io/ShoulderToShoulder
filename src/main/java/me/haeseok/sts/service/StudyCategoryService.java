package me.haeseok.sts.service;

import me.haeseok.sts.dto.CategoryDTO;
import me.haeseok.sts.dto.StudyCategoryDTO;

import java.util.List;

public interface StudyCategoryService {
    List<StudyCategoryDTO> readStudyCategoryList();
}