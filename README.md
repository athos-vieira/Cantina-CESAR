# Cantina CESAR (versão go-horse)


## O que é o app
Nos 15 minutos de intervalo, a fila da cantina come metade do tempo. Pior: quem quer um item rápido, como a coxinha, espera na mesma fila de quem pediu um item demorado, como a tapioca. Muita gente desiste ou compra fora.

O **Cantina CESAR** é um app de console (PoC) que responde à pergunta do aluno: **"ainda dá tempo de pegar isso?"**

**O que ele faz hoje:**
- Mostra o cardápio com **preço, tempo estimado e tipo** de cada item (rápido ou preparado na hora)
- Pergunta **quantos minutos faltam** no intervalo
- **Avisa se ainda dá tempo** a cada item adicionado ao pedido (já reservando 3 minutos para comer)
- Recebe o **pagamento** por Pix, cartão ou vale-refeição
- Mostra o **status do pedido**: PAGO → EM PREPARO → PRONTO

**Base da ideia:** entrevistas com 6 pessoas da equipe e 1 conversa com a cantina. Ainda não ouvimos alunos de fora.

**Próximo passo (PoC final):** separar os pedidos em fila de itens rápidos e fila de itens preparados na hora.

> Preços e tempos de preparo são **estimados** (não temos os dados reais da cantina ainda).

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

Todos os integrantes sabem explicar o projeto, e a IA ajudou a escrever o código.

## Como rodar

**Pré-requisito:** JDK 17 ou mais novo (não precisa de Maven, Gradle nem bibliotecas).

| Sistema | Instalar o JDK |
|---|---|
| Windows | `winget install EclipseAdoptium.Temurin.17.JDK` ou instalador em adoptium.net |
| macOS | `brew install --cask temurin@17` |
| Linux (Ubuntu/Debian) | `sudo apt install openjdk-17-jdk` |

Confira com `java -version` e `javac -version` (os dois devem mostrar 17 ou mais).

**Baixar o projeto:** `git clone <url-do-repositório>` (ou *Code → Download ZIP* e extrair).

**Rodar** (no terminal, dentro da pasta que contém `src/`):
```bash
javac -encoding UTF-8 -d out src/*.java
java -Dfile.encoding=UTF-8 -cp out Main
```
- O primeiro comando compila para a pasta `out/`; o segundo executa a classe `Main`.
- Funciona em CMD, Git Bash, Terminal do macOS, terminais Linux e no terminal do VS Code.
- No PowerShell, troque `src/*.java` por `(Get-ChildItem src\*.java)`.

**Alternativas:** abrir a pasta no IntelliJ ou VS Code (extensão *Extension Pack for Java*) e clicar em Run no `Main.java`; ou usar o **GitHub Codespaces** (*Code → Codespaces*), que já vem com Java.

## Onde estão os princípios no código
Esta é a versão **go-horse** (feita para rodar rápido): parte dos princípios já aparece no código, e o restante será aplicado na PoC final.

| Princípio | Onde | Status |
|---|---|---|
| Inversão de Dependências | `Item.java`, `Pagamento.java`: o `Main` trabalha com as interfaces, não com as classes concretas | 
| Aberto/Fechado | `Pix`, `CartaoCredito`, `ValeRefeicao`, `Coxinha`, `Tapioca`...: novo item ou pagamento = classe nova, sem mexer nas outras | (o `Main` ainda registra à mão) |
| Segregação de Interfaces | `Item` (4 métodos) e `Pagamento` (2 métodos) são pequenas | 
| Ocultamento de informação | `limite` e `saldo` privados em `CartaoCredito` e `ValeRefeicao` | parcial: `Pedido.status` e `Pedido.itens` são públicos |
| Responsabilidade Única / Coesão | `Main.java` mistura tela, regra de tempo e fluxo | a corrigir |
| Integridade conceitual | itens seguem o mesmo padrão; pagamentos também | parcial |

