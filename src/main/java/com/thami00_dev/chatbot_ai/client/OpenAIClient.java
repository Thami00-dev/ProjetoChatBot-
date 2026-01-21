package com.thami00_dev.chatbot_ai.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component 
public class OpenAIClient {

    private final String apiKey;
    private final String systemPrompt;
    private final HttpClient httpClient;

    public OpenAIClient() throws IOException {
        // Carrega as variáveis do arquivo .env
        Map<String, String> env = loadEnvFile();
        
        this.apiKey = env.get("OPENAI_API_KEY");
        
        if (this.apiKey == null || this.apiKey.isEmpty()) {
            throw new IllegalStateException(
                "OPENAI_API_KEY não encontrada! Crie um arquivo .env na raiz do projeto."
            );
        }
        
        // Carrega o prompt do sistema
        this.systemPrompt = Files.readString(
                Path.of("src/main/resources/prompts/system.txt")
        );
        
        this.httpClient = HttpClient.newHttpClient();
    }

    private Map<String, String> loadEnvFile() throws IOException {
        Map<String, String> env = new HashMap<>();
        Path envPath = Path.of(".env");
        
        if (!Files.exists(envPath)) {
            return env;
        }
        
        Files.readAllLines(envPath).forEach(line -> {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    env.put(parts[0].trim(), parts[1].trim());
                }
            }
        });
        
        return env;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public String sendMessage(String userId, String message) {
        try {
            String body = String.format("""
            {
              "model": "gpt-4o-mini",
              "messages": [
                {"role": "system", "content": "%s"},
                {"role": "user", "content": "%s"}
              ],
              "temperature": 0.7,
              "max_tokens": 500
            }
            """,
                escapeJson(systemPrompt),
                escapeJson(message)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            // Log da resposta completa
           // System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            if (response.statusCode() != 200) {
                System.err.println("Erro da API OpenAI: " + response.body());
                return "Erro ao se comunicar com a IA (código: " + response.statusCode() + ")";
            }

            return extractContent(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao chamar a OpenAI: " + e.getMessage();
        }
    }

private String extractContent(String json) {
    try {
        // Procura o padrão: "content": "TEXTO"
        int idx = json.indexOf("\"content\"");
        if (idx == -1) return "Sem resposta da IA";
        
        // Procura a primeira aspas após "content"
        int start = json.indexOf("\"", idx + 9);
        if (start == -1) return "Sem resposta da IA";
        start++; // Pula a aspas de abertura
        
        // Procura a aspas de fechamento (cuidado com escape \")
        int end = start;
        while (end < json.length()) {
            if (json.charAt(end) == '"' && json.charAt(end - 1) != '\\') {
                break;
            }
            end++;
        }
        
        if (end >= json.length()) return "Sem resposta da IA";
        
        String content = json.substring(start, end);
        
        // Decodifica escapes JSON
        return content
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
                
    } catch (Exception e) {
        System.err.println("Erro ao extrair conteúdo: " + e.getMessage());
        e.printStackTrace();
        return "Erro ao processar resposta";
    }
}
    private String escapeJson(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}



