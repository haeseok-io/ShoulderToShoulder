package me.haeseok.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoimDetailDTO {
    private Long no;
    private String contents;
    private Integer price;
    private String location;
    private Integer onlineNo;
    private Integer categoryNo;
}
