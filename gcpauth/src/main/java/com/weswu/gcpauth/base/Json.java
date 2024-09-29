package com.weswu.gcpauth.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

/**
 * @author weswu
 * @date 2021/05/23
 */
@Component
public class Json {
    static final com.google.gson.Gson gson = new com.google.gson.Gson();

    public static String encode(Object object) throws JsonProcessingException {
        return gson.toJson(object);
    }

    public static <T> T decode(String data, Class<T> clazz) throws GAException
    {
        try {
            return (T) gson.fromJson(data,clazz);
        } catch(Exception ex)
        {
            throw new GAException("400", "Invalid data: " + ex.getMessage());
        }
    }

}
