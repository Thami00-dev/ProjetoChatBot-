const sendBtn = document.getElementById("sendBtn");
const input = document.getElementById("messageInput");
const responseDiv = document.getElementById("response");

sendBtn.addEventListener("click", sendMessage);

async function sendMessage() {
  const message = input.value;

  if (!message) {
    responseDiv.innerText = "Digite uma mensagem.";
    return;
  }

  try {
    const res = await fetch("http://localhost:3001/chat", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ message })
    });

    const data = await res.json();
    responseDiv.innerText = data.reply;

  } catch (error) {
    responseDiv.innerText = "Erro ao falar com o servidor.";
    console.error(error);
  }
}
