# Estrutura do repositório · Cantina CESAR

Documento de projeto (versão atual): fluxo principal + classes de **itens** e **pagamento**.
Preços e tempos são **estimativas** (ver `Estimativas.java`).

## 1. Árvore de arquivos

```
cantina-cesar/
├── README.md
├── .gitignore
├── docs/
│   └── ESTRUTURA.md                  ← este documento
└── src/cantina/
    ├── Main.java                     ← composition root: monta e injeta tudo
    ├── config/
    │   └── Estimativas.java          ← ÚNICO lugar de preços/tempos/margens
    ├── item/
    │   ├── Item.java                 ← interface (abstração do produto)
    │   ├── TipoItem.java             ← enum RAPIDO | PREPARADO
    │   ├── Coxinha.java              ← rápido
    │   ├── Refrigerante.java         ← rápido
    │   ├── Tapioca.java              ← preparado na hora
    │   └── Cuscuz.java               ← preparado na hora
    ├── pagamento/
    │   ├── Pagamento.java            ← interface (abstração)
    │   ├── ResultadoPagamento.java   ← record (aprovado, mensagem)
    │   ├── Pix.java
    │   ├── CartaoCredito.java
    │   └── ValeRefeicao.java
    ├── pedido/
    │   ├── Pedido.java               ← entidade; status só muda por métodos
    │   ├── StatusPedido.java         ← enum CRIADO → PAGO → EM_PREPARO → PRONTO
    │   └── FilaDePedidos.java        ← duas filas: rápidos x preparados
    ├── servico/
    │   ├── Cardapio.java             ← lista de Item (recebe por construtor)
    │   ├── VerificadorDeTempo.java   ← regra "ainda dá tempo?"
    │   ├── Avaliacao.java            ← record com o veredito
    │   └── CantinaService.java       ← orquestra o fluxo (sem I/O)
    └── ui/
        └── TelaConsole.java          ← ÚNICA classe que lê/escreve no console
```

## 2. Fluxo principal

```
Main
 └─ TelaConsole.executar()
     1. mostra cardápio (preço, tempo, tipo)          ← Cardapio
     2. pergunta "quantos minutos faltam?"
     3. aluno escolhe itens; a cada item:
        CantinaService.avaliar(...) → "dá tempo?"     ← VerificadorDeTempo + FilaDePedidos
     4. aluno escolhe a forma de pagamento
     5. CantinaService.confirmar(pedido, pagamento)   ← Pedido.pagar(Pagamento)
        └─ aprovado → entra na fila RAPIDO ou PREPARADO
     6. simulação da cozinha: EM_PREPARO → PRONTO     ← Pedido.iniciarPreparo()/marcarPronto()
```

## 3. Classes e responsabilidades

| Classe | Responsabilidade (um motivo para mudar) | Depende de |
|---|---|---|
| `Estimativas` | guardar números estimados | nada |
| `Item` | contrato: nome, preço, tempo, tipo | `TipoItem` |
| `Coxinha`, `Refrigerante` | item rápido | `Item`, `Estimativas` |
| `Tapioca`, `Cuscuz` | item preparado na hora | `Item`, `Estimativas` |
| `Pagamento` | contrato: `pagar(valor)` | `ResultadoPagamento` |
| `Pix`, `CartaoCredito`, `ValeRefeicao` | regra de cada meio de pagamento | `Pagamento` |
| `Pedido` | itens + status + total | `Item`, `Pagamento` (abstrações) |
| `FilaDePedidos` | organizar espera por tipo | `Pedido`, `TipoItem` |
| `VerificadorDeTempo` | decidir se cabe no intervalo | nada |
| `CantinaService` | orquestrar o caso de uso | `Cardapio`, `FilaDePedidos`, `VerificadorDeTempo` |
| `TelaConsole` | interação com o usuário | `CantinaService`, `Pagamento`, `Item` |
| `Main` | instanciar e ligar as peças | todas (único lugar que conhece as concretas) |
```