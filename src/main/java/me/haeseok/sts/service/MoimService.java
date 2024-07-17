package me.haeseok.sts.service;

import me.haeseok.sts.request.MoimListRequest;
import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.response.CustomPageResponse;
import me.haeseok.sts.response.MoimListResponse;
import me.haeseok.sts.util.Result;

public interface MoimService {
    Result addMoim(MoimWriteRequest request);
    CustomPageResponse<MoimListResponse> readMoimList(MoimListRequest request);
}
