package com.thami00_dev.chatbot_ai.service;

import org.springframework.stereotype.Service;

import com.thami00_dev.chatbot_ai.client.OpenAIClient;

@Service
public class ChatService {

    private final OpenAIClient client;

    public ChatService(OpenAIClient client) {
        this.client = client;
    }

    public String processMessage(String userId, String message) {
        return client.sendMessage(userId, message);
    }
}
