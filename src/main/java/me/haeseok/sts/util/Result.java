package me.haeseok.sts.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private String message;

    public static Result success(String message) {
        return new Result(true, message);
    }

    public static Result failure(String message) {
        return new Result(false, message);
    }
}
