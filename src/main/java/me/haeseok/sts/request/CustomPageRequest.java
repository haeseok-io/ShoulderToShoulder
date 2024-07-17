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
        Sort sort = null;
        String sortField = props[0];
        String sortType = props[1];

        // Check
        if( sortType.equals("asc") )        sort = Sort.by(sortField).ascending();
        else if( sortType.equals("desc") )  sort = Sort.by(sortField).descending();

        // Result
        return PageRequest.of(this.page-1, this.scale, sort);
    }
}
