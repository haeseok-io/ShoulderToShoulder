package me.haeseok.sts.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LevelMapper {
    private static final Map<Integer, String> levelMap = Map.of(
            1, "초심자",
            2, "초보",
            3, "중수",
            4, "고수",
            5, "초고수"
    );

    public static String getLevelText(Integer level) {
        return levelMap.getOrDefault(level, "미지정");
    }
}
