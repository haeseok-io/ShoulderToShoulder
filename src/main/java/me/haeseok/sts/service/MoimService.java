package me.haeseok.sts.service;

import me.haeseok.sts.request.MoimWriteRequest;
import me.haeseok.sts.util.Result;

public interface MoimService {
    Result addMoim(MoimWriteRequest request);
}
