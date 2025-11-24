# 🛒 TechOrder — Sistema de Gestão de Pedidos e Estoque  
📦 *Projeto desenvolvido para a disciplina de Banco de Dados 2*

**Alunos:** Seu Nome, Nome do Seu Amigo  
**Data:** [Data de Hoje]  

---

## 📘 1. Introdução e Objetivo

O **TechOrder** é uma aplicação *Full Stack* para gerenciamento de vendas e estoque de um e-commerce de produtos eletrônicos.

Diferente de um simples CRUD, o foco do projeto é **garantir integridade, atomicidade e consistência dos dados** utilizando recursos avançados de SGBDs relacionais, como:

- Stored Procedures  
- Triggers  
- Transações ACID  

Toda a lógica crítica de negócios — especialmente o processamento de pedidos — é executada *no próprio banco de dados*, garantindo que as regras sejam preservadas independentemente da interface utilizada.

---

## 📚 2. Definição do Domínio

O sistema simula o back-office e o fluxo de vendas de uma loja de informática. O principal problema resolvido é a **concorrência em vendas de produtos com estoque limitado**.

### Cenários contemplados:

- Cliente tenta comprar um produto que acabou de esgotar.  
- Alterações de preços precisam ser rastreadas para evitar fraudes internas.  
- Um pedido **só pode ser criado** se *todos* os itens tiverem estoque disponível (atomicidade).  

---

## 🗂️ 3. Estrutura de Dados (Modelagem)

O banco segue rigorosamente a **Terceira Forma Normal (3FN)**.  
As principais entidades são:

### 🧑‍💼 Clientes
Armazena informações dos compradores.

### 🖥️ Produtos
- Nome  
- Preço atual  
- Quantidade em estoque  

### 🧾 Pedidos
Registra o cabeçalho da venda:  
- Data  
- Cliente  
- Valor total  
- Status  

### 📦 ItensPedido
Tabela associativa entre pedidos e produtos, contendo:  
- Quantidade vendida  
- Preço no momento da compra  

### 📝 LogPrecos (Auditoria)
Registra o histórico de alteração de preços (via Trigger).

---

## ⚙️ 4. Regras de Negócio e Implementação no SGBD

A robustez do sistema está baseada em três elementos principais:

---

### 🔧 4.1 Stored Procedure: `sp_RealizarPedido`
Responsável por orquestrar toda a operação crítica de venda.

#### Funcionalidades:
- Recebe o cliente e a lista de produtos/quantidades  
- Inicia uma transação  
- Verifica estoque  
- Cria o pedido  
- Insere itens  
- Atualiza estoque  
- Calcula total  
- Executa `COMMIT` ou `ROLLBACK` em caso de erro  

🔒 **Garante atomicidade:** ou tudo é feito, ou nada é feito.

---

### 📝 4.2 Trigger: `trg_AuditoriaAlteracaoPreco`
Controla histórico de preços para segurança.

#### Detalhes:
- Executado após `UPDATE` em *Produtos*  
- Registra no LogPrecos:  
  - Preço antigo  
  - Preço novo  
  - Data/hora  
  - Usuário  

---

### 🚫 4.3 Trigger: `trg_ValidarEstoqueMinimo`
Evita que qualquer operação deixe o estoque negativo.

- Executado **antes** de atualizar o estoque  
- Impede a operação  
- Lança exceção personalizada capturada pelo backend  

---

## 🏗️ 5. Arquitetura da Aplicação

O sistema é dividido em três camadas principais:

---

### 🎨 5.1 Frontend — *Next.js*

Telass:
- Listagem de produtos  
- Carrinho de compras  
- Tela administrativa  
- Relatórios de auditoria  

O frontend apenas exibe mensagens retornadas pelo banco (sucesso/erro).

---

### 🐍 5.2 Backend — *Python (API)*

Backend propositalmente fino (“Thin Controller”).  
Responsável por:

- Receber JSON do frontend  
- Invocar a Stored Procedure  
- Tratar exceções do banco  
- Retornar erros como respostas HTTP  

---

### 🗄️ 5.3 Integração Aplicação ↔ Banco

As regras **não dependem da aplicação**.  
Mesmo se alguém inserir um pedido manualmente via SQL:

- Estoque será atualizado  
- Auditoria funcionará  
- Atomicidade garantida  

Tudo porque a lógica está no SGBD.

---

## ✅ 6. Conclusão

O **TechOrder** demonstra como recursos avançados de SGBDs relacionais — Procedures, Triggers e Transações — elevam a confiabilidade e segurança de um sistema comercial.

A lógica crítica sendo executada no banco:

- Evita inconsistências  
- Garante integridade mesmo sob alta concorrência  
- Permite auditoria confiável  
- Reduz riscos de fraudes e erros de implementação  

O projeto cumpre plenamente os requisitos da disciplina de Banco de Dados 2 e exemplifica boas práticas de desenvolvimento integrado com SGBDs.

---

