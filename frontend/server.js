const fetch = (...args) =>
  import("node-fetch").then(({ default: fetch }) => fetch(...args));

const express = require("express");
const cors = require("cors");
const path = require("path");

const app = express();
const PORT = 3001;

app.use(cors());
app.use(express.json());

app.use(express.static(path.join(__dirname, "public")));

app.post("/chat", async (req, res) => {
  const { message } = req.body;

  try {
    const response = await fetch("http://localhost:8080/api/chat", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        message,
        userId: "frontend-user",
      }),
    });
    if (!response.ok) {
  throw new Error("Erro HTTP: " + response.status);
  }

    const data = await response.json();
    res.json({ reply: data.answer });


  } catch (error) {
    console.error(error);
    res.status(500).json({ reply: "Erro ao falar com o backend Java" });
  }
});




app.listen(PORT, () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
});
