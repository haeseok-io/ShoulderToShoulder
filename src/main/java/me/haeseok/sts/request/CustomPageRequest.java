package me.haeseok.sts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageRequest {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int scale = 10;
    @Builder.Default
    private int pageScale = 10;
    private String queryString;

    public Pageable getPageable(String ... props) {
        PageRequest pageRequest = PageRequest.of(this.page-1, this.scale, Sort.by(props).descending());
        return pageRequest;
    }
}
