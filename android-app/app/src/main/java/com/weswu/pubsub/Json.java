package com.weswu.pubsub;

/**
 * @author weswu
 * @date 2021/05/23
 */
public class Json {
    static final com.google.gson.Gson gson = new com.google.gson.Gson();

    public static String encode(Object object) throws Exception {
        return gson.toJson(object);
    }

    public static <T> T decode(String data, Class<T> clazz) throws Exception
    {
        try {
            return (T) gson.fromJson(data,clazz);
        } catch(Exception ex)
        {
            throw new Exception("Invalid data: " + ex.getMessage());
        }
    }

}
