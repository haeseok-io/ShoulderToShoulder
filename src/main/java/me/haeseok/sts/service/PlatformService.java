package me.haeseok.sts.service;

import me.haeseok.sts.dto.PlatformDTO;

import java.util.List;

public interface PlatformService {
    List<PlatformDTO> readPlatformList();
}
