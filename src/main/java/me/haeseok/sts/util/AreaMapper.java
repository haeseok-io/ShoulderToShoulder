package me.haeseok.sts.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AreaMapper {
    private static final Map<Integer, String> areaMap = Map.ofEntries(
            Map.entry(1, "상관없음"),
            Map.entry(2, "서울특별시"),
            Map.entry(3, "경기도"),
            Map.entry(4, "경기남부"),
            Map.entry(5, "경기북부"),
            Map.entry(6, "부산광역시"),
            Map.entry(7, "인천광역시"),
            Map.entry(8, "대구광역시"),
            Map.entry(9, "경상남도"),
            Map.entry(10, "경상북도"),
            Map.entry(11, "대전광역시"),
            Map.entry(12, "충청남도"),
            Map.entry(13, "충청북도"),
            Map.entry(14, "전라남도"),
            Map.entry(15, "전라북도"),
            Map.entry(16, "광주광역시"),
            Map.entry(17, "강원도"),
            Map.entry(18, "울산광역시"),
            Map.entry(19, "제주특별자치도"),
            Map.entry(20, "세종특별자치시")
    );

    public static String getAreaText(Integer area) {
        return areaMap.getOrDefault(area, "미지정");
    }
}
