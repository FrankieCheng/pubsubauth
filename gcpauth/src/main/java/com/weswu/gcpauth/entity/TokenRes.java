package com.weswu.gcpauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author weswu
 * @date 2021/5/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRes implements Serializable {
    private String access_token; // ya29.xxx
    private int expires_in; //seconds
    private String token_type;
}
