package org.dx.chatroomdemo.Entity;


import lombok.Data;

@Data
public class ChatMessage {
    private Type type;
    private String content;
    private String sender;
    private String receiver;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public enum Type {
        CHAT, // 聊天消息
        JOIN, // 加入聊天室
        LEAVE // 离开聊天室
    }
}
