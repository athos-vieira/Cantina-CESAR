# Cantina CESAR

1. Informa quantos minutos faltam no intervalo
2. Escolhe os itens (o app avisa se ainda dá tempo)
3. Paga com Pix, cartão ou vale-refeição
4. Acompanha o status: PAGO → EM PREPARO → PRONTO


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
| _(nome)_ | _(ex.: 
| _(nome)_ | _(ex.:
| _(nome)_ | _(ex.:
| _(nome)_ | _(ex.: 

## Como rodar (Java 17+)
```bash
javac -encoding UTF-8 -d out src/*.java
java -Dfile.encoding=UTF-8 -cp out Main
```

## Onde estão os princípios no código
Esta é a versão **go-horse**: alguns princípios já aparecem, 

| Princípio | Onde | Status |
|---|---|---|
| Inversão de Dependências | `Item.java`, `Pagamento.java`: o `Main` trabalha com as interfaces, não com as classes concretas | ✅ |
| Aberto/Fechado | `Pix`, `CartaoCredito`, `ValeRefeicao`, `Coxinha`, `Tapioca`...: novo item ou pagamento = classe nova, sem mexer nas outras | ✅ (o `Main` ainda registra à mão) |
| Segregação de Interfaces | `Item` (4 métodos) e `Pagamento` (2 métodos) são pequenas | ✅ |
| Ocultamento de informação | `limite` e `saldo` privados em `CartaoCredito` e `ValeRefeicao` | ⚠️ parcial: `Pedido.status` e `Pedido.itens` são públicos |
| Responsabilidade Única / Coesão | `Main.java` mistura tela, regra de tempo e fluxo | ❌ a corrigir |
| Integridade conceitual | itens seguem o mesmo padrão; pagamentos também | ✅ parcial |

Estrutura de arquivos e plano de refatoração: [`docs/ESTRUTURA.md`](docs/ESTRUTURA.md).
