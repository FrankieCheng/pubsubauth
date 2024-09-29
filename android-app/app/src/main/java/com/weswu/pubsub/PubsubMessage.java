package com.weswu.pubsub;

//import android.util.Base64;
import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weswu
 * @date 2021/5/25
 */
public class PubsubMessage {
    private String data;
    private Map<String, String> attributes;

    public PubsubMessage(String data) {
        this.data = base64Encode(data);
        this.attributes = new HashMap<String, String>();
    }

    public PubsubMessage(String data, Map<String, String> attributes) {
        this.data = base64Encode(data);
        this.attributes = attributes;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String base64Encode(String data){
        String encodedData = "";
        try {
            byte[] byteStr = data.getBytes("UTF-8");
            encodedData = Base64.encodeBase64String(byteStr);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("bad data.");
        }
        return encodedData;
    }

}
