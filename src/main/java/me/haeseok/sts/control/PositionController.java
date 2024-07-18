package me.haeseok.sts.control;

import lombok.RequiredArgsConstructor;
import me.haeseok.sts.dto.PositionDetailDTO;
import me.haeseok.sts.service.PositionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/position")
public class PositionController {
    private final PositionService positionService;

    @ResponseBody
    @GetMapping(value = "/{no}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PositionDetailDTO> getPositionDetailList(@PathVariable("no") Integer no) {
        return positionService.readPositionDetailByPositionNo(no);
    }
}
