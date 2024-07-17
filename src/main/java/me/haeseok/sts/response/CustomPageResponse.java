package me.haeseok.sts.response;

import lombok.Builder;
import lombok.Data;
import me.haeseok.sts.request.CustomPageRequest;

import java.util.List;

@Data
public class CustomPageResponse<E> {
    private int page;
    private int scale;
    private int pageScale;
    private int total;
    private String queryString;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dataList;

    @Builder(builderMethodName = "pageBuilder")
    public CustomPageResponse(CustomPageRequest request, List<E> dataList, int total) {
        if( total<1 )   return;

        this.page = request.getPage();
        this.scale = request.getScale();
        this.pageScale = request.getPageScale();
        this.queryString = request.getQueryString();
        this.dataList = dataList;
        this.total = total;

        this.end = (int)(Math.ceil(this.page/ (double) this.pageScale))*this.pageScale;
        this.start = (this.end-this.pageScale)+1;

        // 이 값을 넘는 갖장 작은 수
        int last = (int)(Math.ceil(this.total/(double)this.scale));

        // 데이터의 갯수를 계산한 마지막 페이지 번호
        this.end = Math.min(end, last);

        this.prev = this.start>1;
        this.next = total>this.end*this.scale;
    }
}
