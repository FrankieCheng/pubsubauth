package com.weswu.gcpauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weswu
 * @date 2021/5/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenReq {
    private String guid;
    private String scope;
}
