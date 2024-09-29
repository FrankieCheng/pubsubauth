package com.weswu.pubsub;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author weswu
 * @date 2021/5/25
 */
public class PubsubPublisher {

  public Logger logger = Logger.getLogger(getClass().getName());

  public final MediaType JsonType = MediaType.get("application/json; charset=utf-8");
  public final OkHttpClient httpClient = new OkHttpClient();
  public final String pubsubUrl = "https://pubsub.googleapis.com:443";

  public HttpRes post(String url, String data, String token) {
    HttpRes httpRes = null;
    RequestBody body = RequestBody.create(JsonType, data);
    Request request = new Request.Builder()
        .url(url)
        .addHeader("Authorization", "Bearer " + token)
        .post(body)
        .build();
    try {
      Response response = httpClient.newCall(request).execute();
      httpRes = new HttpRes(response.code(), response.body().string());
    } catch (Exception ex) {
      //log error here
      logger.info(ex.getMessage());
    }
    return httpRes;
  }

  public String get_google_access_token() throws Exception {
    String access_token = null;
    TokenReq tokenReq = new TokenReq();
    tokenReq.setGuid("0548e3d23304dcc26cb855c1fb96dc53");
    tokenReq.setScope("https://www.googleapis.com/auth/pubsub");
    String tokenReqStr = Json.encode(tokenReq);
    HttpRes publishRes = post(ConfigConstant.GCP_AUTH_URL, tokenReqStr, ConfigConstant.GCP_AUTH_TOKEN);
    if (null != publishRes && publishRes.getCode() == 200) {
      JsonObject authResJson = JsonParser.parseString(publishRes.getData()).getAsJsonObject();
      access_token = authResJson.get("data").getAsJsonObject().get("tokenValue").getAsString();
    }
    return access_token;
  }

  public void publish(List<PubsubMessage> messages, String topicName) {
    try {
      String accessToken = get_google_access_token();
      if (null == accessToken) {
        throw new Exception("No Access Token Found.");
      }

      String fullUrl = String.format("%s/v1/projects/%s/topics/%s:publish", pubsubUrl, ConfigConstant.PROJECT_ID,
              topicName);
      PubsubMessages pubsubMessages = new PubsubMessages(messages);
      String pubsubMessagesStr = Json.encode(pubsubMessages);
      HttpRes publishRes = post(fullUrl, pubsubMessagesStr, accessToken);
      logger.info("http request status code = " + publishRes.getCode());
    } catch (Exception ignored) {

    }
  }
}
