package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.StudyCategoryDAO;
import me.haeseok.sts.dto.CategoryDTO;
import me.haeseok.sts.dto.StudyCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyCategoryServiceImple implements StudyCategoryService {
    private final StudyCategoryDAO studyCategoryDAO;

    @Override
    public List<StudyCategoryDTO> readStudyCategoryList() {
        return studyCategoryDAO.getStudyCategoryList();
    }
}
