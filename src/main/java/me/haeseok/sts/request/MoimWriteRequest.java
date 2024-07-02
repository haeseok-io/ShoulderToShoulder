package me.haeseok.sts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimWriteRequest {
    private String type;
    private String subject;
    private String desc;
    private MultipartFile thumbnail;
    private Integer categoryNo;
    private Integer studyCategoryNo;
    private List<Integer> platformNoList;
    private Integer onlineNo;
    private String location;
    private Integer price;
    private List<HeadcountGroupRequest> headcountGroupList;
    private String contents;
    private List<String> language;
    private List<LinkGroupRequest> linkGroupList;
}
