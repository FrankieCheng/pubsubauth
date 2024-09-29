package com.weswu.gcpauth.utils;

import com.weswu.gcpauth.base.GAException;
import com.weswu.gcpauth.base.ResStatus;


import java.io.IOException;

/**
 * @author weswu
 * @date 2021/05/23
 */
public class Utils {
    public static GAException format_exception(Exception e){
        if (e instanceof GAException){
            return (GAException) e;
        }
        else if (e instanceof IOException){
            e.printStackTrace();
            return new GAException(ResStatus.IO_ERROR.getStatus(), e.getMessage());
        }
        else if (e instanceof RuntimeException) {
            e.printStackTrace();
            return new GAException(ResStatus.GA_ERROR.getStatus(), e.getMessage());
        }
        else{
            e.printStackTrace();
            return new GAException(ResStatus.UNKOWN_ERROR, e.getMessage());
            
        }
    }

}
