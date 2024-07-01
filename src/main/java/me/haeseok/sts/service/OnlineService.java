package me.haeseok.sts.service;

import me.haeseok.sts.dto.OnlineDTO;

import java.util.List;

public interface OnlineService {
    List<OnlineDTO> readOnlineList();
}
