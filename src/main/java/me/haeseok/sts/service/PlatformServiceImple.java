package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.PlatformDAO;
import me.haeseok.sts.dto.PlatformDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformServiceImple implements PlatformService {
    private final PlatformDAO platformDAO;

    @Override
    public List<PlatformDTO> readPlatformList() {
        return platformDAO.getPlatformList();
    }
}
