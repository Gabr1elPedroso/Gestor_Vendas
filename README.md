# 🔥 GAP Gestão Comercial — Sistema PDV Portátil

Um sistema completo de **Ponto de Venda (PDV), Controle de Estoque e Fluxo de Caixa**, projetado com arquitetura autônoma e portátil (*Zero-Installation*). Todo o ecossistema — backend, frontend e banco de dados — é empacotado para rodar localmente de forma simples e rápida, sem exigir configuração de servidores externos na máquina do cliente.

---

## 🚀 Principais Funcionalidades

* **⚡ Frente de Caixa (PDV):**
  * Montagem dinâmica de carrinho de compras com validação automática de disponibilidade em estoque.
  * Remoção rápida de itens antes da finalização e cálculo automático de subtotais e valor total.
  * Baixa automática de estoque e registro simultâneo de entrada no fluxo de caixa via transações atômicas (`@Transactional`).
* **📲 Pagamento Pix Integrado (BR Code):**
  * Geração automática de payload **Pix Copia e Cola** seguindo o padrão EMV do Banco Central.
  * Renderização instantânea de **QR Code** dinâmico na tela de conclusão da venda.
  * Exportação formatada do **Resumo do Pedido** com um clique, pronta para envio direto ao cliente via WhatsApp.
* **📦 Gestão de Estoque (CRUD + Soft Delete):**
  * Cadastro, edição e pesquisa rápida de produtos por **ID** ou **Nome**.
  * **Exclusão Lógica (Soft Delete):** Produtos com histórico de vendas são inativados em vez de apagados fisicamente, preservando a integridade dos relatórios financeiros anteriores.
* **💸 Controle de Caixa & Relatórios:**
  * Acompanhamento do saldo atual em tempo real no cabeçalho do sistema.
  * Registro manual de **Saídas / Sangrias** (pagamento de fornecedores, despesas operacionais).
  * Extrato detalhado de movimentações (Entradas e Saídas) com filtro por ID.

---

## 🛠️ Tecnologias Utilizadas

* **Back-End:** Java 17+, Spring Boot (Spring Web, Spring Data JPA), Maven.
* **Banco de Dados:** H2 Database em modo arquivo local (`./dados/posdb`), dispensando instalação de SGBDs externos.
* **Front-End:** SPA (Single Page Application) integrada em HTML5, JavaScript (Vanilla ES6+) e **Tailwind CSS**, com geração de QR Code via `qrcode.js` e sistema de notificações *Toast* personalizadas.

---

## ⚙️ Como Funciona a Arquitetura

O sistema segue o padrão **Monolito Modular Portátil**:
1. Ao iniciar a aplicação, o Spring Boot sobe o servidor web embutido (Tomcat) na porta `8080` e conecta-se automaticamente ao arquivo de banco de dados local na pasta `./dados/`.
2. Os arquivos estáticos da interface (`index.html`) são servidos diretamente pelo Spring Boot a partir de `src/main/resources/static/`, comunicando-se com a API REST (`/api/produtos`, `/api/vendas`, `/api/movimentos-caixa`).
3. Isso permite que toda a aplicação seja distribuída como um **único arquivo executável (`.jar`)**.

---

## 🧪 Como Executar e Testar

### Pré-requisitos
* **Java JDK 17** ou superior instalado.
* **Maven** (ou utilize o wrapper `./mvnw` incluído no projeto).

### Passo 1: Clonar o repositório
```bash
git clone [https://github.com/seu-usuario/gestao-negocios-pdv.git](https://github.com/seu-usuario/gestao-negocios-pdv.git)
cd gestao-negocios-pdv
