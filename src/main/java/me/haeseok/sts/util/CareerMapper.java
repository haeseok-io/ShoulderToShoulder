package me.haeseok.sts.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CareerMapper {
    private static final Map<Integer, String> careerMap = Map.of(
            1, "1~3년차",
            2, "3~5년차",
            3, "5~10년차",
            4, "10년차이상"
    );

    public static String getCareerText(Integer career) {
        return careerMap.getOrDefault(career, "미지정");
    }
}
