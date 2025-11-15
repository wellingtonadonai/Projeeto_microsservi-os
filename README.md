Perfeito — vou ajustar o README deixando claro que **o Processador é quem salva os dados no banco**, e não o serviço de Pedidos.

Aqui está a versão revisada, simples, clara e pronta para colocar no GitHub:

---

# Microsserviços – Pedidos, Processador e Notificação

## 📌 Descrição

Este projeto é composto por **3 microsserviços** que trabalham juntos para criar e processar pedidos de forma assíncrona usando RabbitMQ e PostgreSQL.

O fluxo principal é:
**Pedido → RabbitMQ → Processador (salva no banco) → Notificação**

---

## 🧩 Arquitetura dos Microsserviços

### **1️⃣ Serviço de Pedidos**

* Recebe requisições REST para criar um pedido.
* O pedido contém:

  * **Produto**
  * **ItemPedido**
  * **Pedido**
* Após a requisição, o serviço **não grava no banco**.
  Ele **envia o pedido diretamente para uma fila no RabbitMQ**, para ser processado de forma assíncrona.

---

### **2️⃣ Serviço Processador (Responsável por salvar no banco)**

* Escuta a fila do RabbitMQ à procura de novos pedidos.
* Recebe o objeto Pedido enviado pelo serviço de Pedidos.
* **Processa e salva o Pedido no PostgreSQL**.
* Após salvar, envia uma mensagem para outra fila do RabbitMQ, destinada ao serviço de Notificação.
* Registra logs via **SLF4J** indicando cada etapa (recebido → processado → salvo → enviado).

---

### **3️⃣ Serviço de Notificação**

* Escuta a fila de mensagens processadas.
* Recebe uma notificação informando que o pedido foi salvo e finalizado.
* Registra essa notificação (em log ou no banco, dependendo de sua implementação).

---

## 🗄️ Estrutura de Domínio

### **Produto**

Representa os itens disponíveis para compra.

### **ItemPedido**

Relaciona um produto com uma quantidade.

### **Pedido**

Conjunto que contém:

* Itens do pedido
* Dados do cliente
* Valores totais

---

## ⚙️ Tecnologias Utilizadas

* **Java + Spring Boot**
* **RabbitMQ** — mensageria entre serviços
* **PostgreSQL** — persistência
* **Swagger** — documentação de API
* **Docker / Docker Compose** — subir serviços rapidamente
* **SLF4J** — logs estruturados

---

## ▶️ Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/wellingtonadonai/Projeeto_microsservi-os.git
cd Projeeto_microsservi-os
```

### 2. Execute com Docker Compose

```bash
docker-compose up
```

Isso iniciará:

* PostgreSQL
* RabbitMQ
* Serviço de Pedidos
* Serviço Processador
* Serviço de Notificação

### 3. Acesse o Swagger

Cada serviço expõe seu próprio Swagger:

```
http://localhost:{porta}/swagger-ui.html
```

---

## 📤 Fluxo Completo do Método de Salvar

1. O cliente chama o endpoint **POST /pedido** no serviço de Pedidos.
2. O serviço de Pedidos **não salva no banco** — ele envia o objeto para o RabbitMQ.
3. O serviço **Processador** recebe a mensagem, cria as entidades e **salva no PostgreSQL**.
4. Após salvar, o Processador envia uma nova mensagem para outra fila.
5. O Serviço de **Notificação** escuta essa fila e registra a notificação.
6. Logs via **SLF4J** mostram todo o fluxo.

---

## 📝 Exemplo de JSON enviado ao serviço de Pedidos Teste no Postman no metodo Post

```json
{
    "cliente":"wellington",
    "itens":[
    {
         "quantidade": 4,
         "produto":{
            "nome": "cadeiras",
            "valor": 30.00
            }
    }
    ],
    "valorTotal": 214.00,
    "emailNotificacao": "deustremendo123@gmail.com"
}
```

---

## 🤝 Contribuição

1. Faça um fork
2. Crie uma nova branch (`git checkout -b minha-feature`)
3. Faça suas melhorias
4. Envie um Pull Request

---

Se quiser, posso **gerar também um diagrama em imagem** mostrando o fluxo dos serviços!
