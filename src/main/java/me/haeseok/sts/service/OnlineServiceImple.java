package me.haeseok.sts.service;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dao.OnlineDAO;
import me.haeseok.sts.dto.OnlineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnlineServiceImple implements OnlineService {
    private final OnlineDAO onlineDAO;

    @Override
    public List<OnlineDTO> readOnlineList() {
        return onlineDAO.getOnlineList();
    }
}
