package com.thami00_dev.chatbot_ai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thami00_dev.chatbot_ai.dto.ChatRequest;
import com.thami00_dev.chatbot_ai.dto.ChatResponse;
import com.thami00_dev.chatbot_ai.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String answer = chatService.processMessage(request.userId(), request.message());
        return new ChatResponse(answer);
    }
}

