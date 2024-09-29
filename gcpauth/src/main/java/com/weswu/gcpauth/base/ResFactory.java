package com.weswu.gcpauth.base;

/**
 * @author weswu
 * @date 2021/05/23
 */
public class ResFactory {
    public static <M> Result<M> wrapper(M data) {
        return new Result<>(data, ResStatus.SUCCESS);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Result wrapper(GAException ex, String err) {
        String errStr = ex.getError() + err;
        return new Result("",ex.getStatus(), errStr);
    }
}
