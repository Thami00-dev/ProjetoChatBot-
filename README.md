# 🤖 ChatBot com Spring Boot e OpenAI

Bot inteligente desenvolvido em **Java + Spring Boot**, integrado com a **API oficial da OpenAI**.

Projeto criado para demonstrar uma arquitetura moderna, limpa e escalável para chatbots com IA generativa.

---

## 🚀 Objetivo do Projeto

Criar um chatbot simples, porém completo, com:

- API REST em Spring Boot  
- DTOs usando **Records (Java moderno)**  
- Serviço de orquestração  
- Cliente OpenAI usando o **SDK oficial**  
- Prompt do sistema configurável por arquivo  
- Suporte a diferentes usuários via `userId`  
- Integração com WhatsApp/Telegram *(em expansão)*  
- Tratamento robusto de erros  
- Logging estruturado  

---

## 📚 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3 
- SDK Oficial OpenAI Java (Responses API)
- Gradle  
- Records (Java moderno)  
- dotenv para variáveis de ambiente  

---

## 🗂 Estrutura do Projeto

```bash
meu-chatbot-ai/
├── src/
│   └── main/
│       ├── java/com/seuNome/chatbotai/
│       │   ├── controller/
│       │   │   └── ChatController.java
│       │   ├── service/
│       │   │   └── ChatService.java
│       │   ├── client/
│       │   │   └── OpenAIClient.java
│       │   ├── dto/
│       │   │   ├── ChatRequest.java
│       │   │   └── ChatResponse.java
│       │   └── ChatbotAiApplication.java
│       └── resources/
│           ├── prompts/
│           │   └── system.txt
│           └── application.properties
├── .env
├── .gitignore
├── build.gradle
└── README.md
```

🔁 Como Funciona
1️⃣ O usuário envia uma requisição

POST /api/chat

{
  "message": "Explique o workshop",
  "userId": "thami"
}

2️⃣ O Controller recebe a requisição

E delega para o serviço.

3️⃣ O Service processa a lógica

Chamando o cliente da OpenAI.

4️⃣ O Client chama a OpenAI

Usando o SDK oficial e anexando a API Key via .env.

5️⃣ O Prompt do sistema define o comportamento da IA

Configurado no arquivo:

src/main/resources/prompts/system.txt


Sem precisar recompilar o projeto.

🔐 Variáveis de Ambiente

Crie um arquivo .env na raiz do projeto:

OPENAI_API_KEY=sua_chave_aqui

▶️ Como Executar
./gradlew bootRun


A API ficará disponível em:

http://localhost:8080/api/chat

📌 Próximos Passos

Integração com WhatsApp / Telegram

Histórico de conversas por usuário

Autenticação

Deploy em cloud (AWS / Railway / Render)


👩‍💻 Autora

Thamires Santo 💻✨

⭐ Se esse projeto te ajudou, deixa uma estrela no repositório!
