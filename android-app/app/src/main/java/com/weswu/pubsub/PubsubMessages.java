package com.weswu.pubsub;

import java.util.List;

/**
 * @author weswu
 * @date 2021/5/25
 */
public class PubsubMessages {
    private List<PubsubMessage> messages;
    public PubsubMessages(List<PubsubMessage> messages) {
        this.messages = messages;
    }
    public List<PubsubMessage> getMessages() {
        return messages;
    }
    public void setMessages(List<PubsubMessage> messages) {
        this.messages = messages;
    }

}
