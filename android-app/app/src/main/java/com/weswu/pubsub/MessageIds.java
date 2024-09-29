package com.weswu.pubsub;

import java.util.List;

/**
 * @author weswu
 * @date 2021/5/25
 */
public class MessageIds {
    private List<String> messageIds;

    public MessageIds(List<String> messageIds) {
        this.messageIds = messageIds;
    }

    public List<String> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(List<String> messageIds) {
        this.messageIds = messageIds;
    }
}
