package com.weswu.pubsub;

/**
 * @author weswu
 * @date 2021/5/25
 */
public class TokenReq {
    private String guid;
    private String scope;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
