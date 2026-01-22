# 🤖 ChatBot com Spring Boot, OpenAI e Node.js

Chatbot inteligente desenvolvido com Java + Spring Boot no backend e Node.js no frontend, integrado à API oficial da OpenAI.

Projeto criado para demonstrar uma arquitetura moderna, limpa e escalável, conectando backend e frontend de forma realista, como em aplicações profissionais.
---

## 🚀 Objetivo do Projeto

Construir um chatbot funcional com IA generativa, contemplando:

-API REST em Spring Boot
-DTOs usando Records (Java moderno)
-Serviço de orquestração
-Cliente OpenAI usando o SDK oficial
-Prompt do sistema configurável por arquivo
-Suporte a múltiplos usuários (userId)
-Frontend simples em Node.js
-Testes iniciais via Postman
-Integração completa Frontend → Backend → OpenAI

##  Arquiterura geral 

Frontend (Node.js + HTML/JS)
        ↓
Backend (Java + Spring Boot)
        ↓
OpenAI API


## 📚 Tecnologias Utilizadas

**Backend**
-Java 17
-Spring Boot 3x
-OpenAI Java SDK 3.x
-Gradle
-Records (Java moderno)
**Frontend**
-Node.js
-Express
-HTML, CSS e JavaScript
-Fetch API
**Outros**
-Postman (para testes iniciais da API)
-dotenv (variáveis de ambiente)
-Git & GitHub

## 🗂 Estrutura do Projeto

```bash
chatbot-ai/
├── backend/                  # Backend Java (Spring Boot)
│   ├── src/main/java/...
│   │   ├── controller/
│   │   ├── service/
│   │   ├── client/
│   │   ├── dto/
│   │   └── ChatbotAiApplication.java
│   └── src/main/resources/
│       ├── prompts/
│       │   └── system.txt
│       └── application.properties
│
├── frontend/                 # Frontend Node.js
│   ├── public/
│   │   ├── index.html
│   │   ├── style.css
│   │   └── app.js
│   ├── server.js
│   └── package.json
│
├── .env
├── .gitignore
└── README.md
```

## ▶️ Como Executar o Projeto

Este projeto possui **backend (Java)** e **frontend (Node.js)**, que devem ser executados separadamente.

---

### 1️⃣ Backend (Java + Spring Boot)

Acesse a pasta do backend:

```bash
cd backend
Execute o projeto:

./gradlew bootRun
O backend ficará disponível em:

http://localhost:8080/api/chat
```
2️⃣ Frontend (Node.js)
Em outro terminal, acesse a pasta do frontend:
```
cd frontend
Instale as dependências (caso ainda não tenha feito):

npm install
```
Inicie o servidor Node:
```
node server.js
O frontend ficará disponível em:

http://localhost:3001
```
🧪 Testes com Postman (fase inicial)
Antes da integração com o frontend, a API foi testada utilizando o Postman.

Endpoint
```
POST

http://localhost:8080/api/chat


Corpo da requisição (JSON)

{
  "message": "Explique este projeto",
  "userId": "thami"
}
```


## 🧠 Prompt do Sistema
O comportamento da IA é definido no arquivo:

backend/src/main/resources/prompts/system.txt

Esse arquivo permite ajustar o tom e as regras da IA sem recompilar o projeto.


2️⃣ O Controller recebe a requisição

E delega para o serviço.

3️⃣ O Service processa a lógica

Chamando o cliente da OpenAI.

4️⃣ O Client chama a OpenAI

Usando o SDK oficial e anexando a API Key via .env.



🔐 Variáveis de Ambiente

Crie um arquivo .env na raiz do projeto:

OPENAI_API_KEY=sua_chave_aqui

📌 Próximos Passos

Melhorar o layout do frontend

Exibir indicador de “IA digitando…”

Histórico de mensagens por usuário

Autenticação

Deploy em nuvem (AWS / Render / Railway)

Integração com WhatsApp ou Telegram


👩‍💻 Autora

Thamires Santo 💻✨

⭐ Se esse projeto te ajudou, deixa uma estrela no repositório!
