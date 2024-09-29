package com.weswu.gcpauth.base;

/**
 * @author weswu
 * @date 2021/05/23
 */
public enum ResStatus implements IMessageEnum {
    SUCCESS("200", ""),
    FAIL("400", "Failed"),
    NOT_FOUND("404", "Not found"),
    FORBIDDEN("403", "Forbidden"),
    REQUEST_TIMEOUT("408", "Timeout"),
    GA_ERROR("500", "Gcp Auth service error"),
    GOOGLE_API_ERROR("500", "Google api service error"),
    IO_ERROR("510", "IO error"),
    UNKOWN_ERROR("520", "Unknown error");

    private String status;
    private String error;

    ResStatus(String status, String error) {
        this.status = status;
        this.error = error;
    }
    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public String getError() {
        return this.error;
    }
}
