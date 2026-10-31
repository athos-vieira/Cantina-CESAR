# Cantina CESAR (versão go-horse)

Hackathon Relâmpago #1 · Cap. 5 · FDS 2026.2 · CESAR School

## O que é o app
Nos 15 minutos de intervalo, a fila da cantina come metade do tempo. Pior: quem quer um item rápido, como a coxinha, espera na mesma fila de quem pediu um item demorado, como a tapioca. Muita gente desiste ou compra fora.

O **Cantina CESAR** é um app de console (PoC) que responde à pergunta do aluno: **"ainda dá tempo de pegar isso?"**

**O que ele faz hoje:**
- Mostra o cardápio com **preço, tempo estimado e tipo** de cada item (rápido ou preparado na hora)
- Pergunta **quantos minutos faltam** no intervalo
- **Avisa se ainda dá tempo** a cada item adicionado ao pedido (já reservando 3 minutos para comer)
- Recebe o **pagamento** por Pix, cartão ou vale-refeição
- Mostra o **status do pedido**: PAGO → EM PREPARO → PRONTO

**Base da ideia:** entrevistas com 6 pessoas da equipe, funcionários da cantina e pessoas de fora.


> tempos de preparo são **estimados** (não temos os dados reais da cantina).

## Decisões da stack
| Decisão | Escolha | Motivo |
|---|---|---|
| Linguagem | Java 17 | OO deixa os princípios do cap. 5 visíveis no código |
| Interface | Console | PoC rápida: foco no projeto, não na tela |
| Build | `javac` puro | sem dependências, roda em qualquer máquina com JDK |
| Dados | Em memória | sem banco nem deploy (não são exigidos) |
| IA | Claude | gerou a base do código; a equipe revisa e explica cada parte |

## Papel dos integrantes
| Integrante | Papel |
|---|---|
| Athos | Código principal (`Main`, `Pedido`) e repositório no GitHub |
| Dani | Vídeo: gravação, edição e legenda |
| Arthur | Classes de itens (`Item`, `Coxinha`, `Refrigerante`, `Tapioca`, `Cuscuz`) |
| Almeida | Classes de pagamento (`Pagamento`, `Pix`, `CartaoCredito`, `ValeRefeicao`) |
| Pierre | README e documentação (`docs/ESTRUTURA.md`) |
| Caio | Testes do fluxo no terminal e conferência dos princípios no código |
| André | Roteiro do vídeo e pesquisa (entrevistas, estimativas de preço e tempo) |


## Como rodar (Java 17+)
```bash
javac -encoding UTF-8 -d out src/*.java
java -Dfile.encoding=UTF-8 -cp out Main
```

## Onde estão os princípios no código


| Princípio | Onde | Status |
|---|---|---|
| Inversão de Dependências | `Item.java`, `Pagamento.java`: o `Main` trabalha com as interfaces, não com as classes concretas | ✅ |
| Aberto/Fechado | `Pix`, `CartaoCredito`, `ValeRefeicao`, `Coxinha`, `Tapioca`...: novo item ou pagamento = classe nova, sem mexer nas outras | ✅ (o `Main` ainda registra à mão) |
| Segregação de Interfaces | `Item` (4 métodos) e `Pagamento` (2 métodos) são pequenas | ✅ |
| Ocultamento de informação | `limite` e `saldo` privados em `CartaoCredito` e `ValeRefeicao` | ⚠️ parcial: `Pedido.status` e `Pedido.itens` são públicos |
| Responsabilidade Única / Coesão | `Main.java` concentra tela, regra de tempo e fluxo | 🔜 será separado na PoC final |
| Integridade conceitual | itens seguem o mesmo padrão; pagamentos também | ✅ parcial |
