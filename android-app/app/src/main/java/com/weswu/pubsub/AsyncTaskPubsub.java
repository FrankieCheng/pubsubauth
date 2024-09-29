package com.weswu.pubsub;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsyncTaskPubsub extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        try {
            List<PubsubMessage> messages = new ArrayList<>();
            PubsubMessage pubsubMessage = new PubsubMessage(Json.encode(buildTestData()));
            messages.add(pubsubMessage);
            new PubsubPublisher().publish(messages, ConfigConstant.TOPIC);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public Map<String, Object> buildTestData() {
        Map<String, Object> pubsubContent = new HashMap<>();
        pubsubContent.put("time", System.currentTimeMillis());
        pubsubContent.put("usage", "Test");
        return pubsubContent;
    }
}
