// Espera o HTML carregar antes de rodar o JS
document.addEventListener("DOMContentLoaded", () => {

  // Elementos do HTML
  const chatBox = document.getElementById("chat-box");
  const form = document.getElementById("chat-form");
  const input = document.getElementById("message");

  // Mensagem inicial da IA
  chatBox.innerHTML += `
    <div class="message bot">
      Oi 💖 Sou uma IA produzida pela thami! Como posso te ajudar?
    </div>
  `;

  // Quando o formulário for enviado
  form.addEventListener("submit", async (event) => {
    event.preventDefault(); // impede reload da página

    const message = input.value.trim();
    if (!message) return;

    // Mensagem do usuário
    chatBox.innerHTML += `
      <div class="message user">
        ${message}
      </div>
    `;

    input.value = "";
    chatBox.scrollTop = chatBox.scrollHeight;

    // Mensagem "Digitando..."
    const typing = document.createElement("div");
    typing.className = "message bot";
    typing.textContent = "Digitando...";
    chatBox.appendChild(typing);
    chatBox.scrollTop = chatBox.scrollHeight;

    try {
      const response = await fetch("http://localhost:3001/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          message
        })
      });

      const data = await response.json();

      typing.remove();

      chatBox.innerHTML += `
  <div class="message bot">
    ${data.reply.replace(/\n/g, "<br>")}
  </div>
`;


      chatBox.scrollTop = chatBox.scrollHeight;

    } catch (error) {
      typing.textContent = "Erro ao se comunicar com o servidor 😢";
    }
  });
});
