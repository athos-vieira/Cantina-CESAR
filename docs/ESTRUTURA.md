
## Árvore de arquivos
```
Cantina-CESAR/
├── docs/
│   └── ESTRUTURA.md
├── README.md
├── .gitignore
└── src/                  ← sem pacotes, tudo na mesma pasta
    ├── Item.java            interface do produto
    ├── Coxinha.java         item rápido
    ├── Refrigerante.java    item rápido
    ├── Tapioca.java         item preparado na hora
    ├── Cuscuz.java          item preparado na hora
    ├── Pagamento.java       interface de pagamento
    ├── Pix.java
    ├── CartaoCredito.java
    ├── ValeRefeicao.java
    ├── Pedido.java          lista de itens, total, tempo e status
    └── Main.java            fluxo principal
```

## Classes

| Classe | O que faz |
|---|---|
| `Item` | contrato: `nome()`, `preco()`, `tempo()`, `tipo()` ("RAPIDO" ou "PREPARADO") |
| `Coxinha`, `Refrigerante` | itens rápidos (1 min) |
| `Tapioca`, `Cuscuz` | itens preparados na hora (5 e 4 min) |
| `Pagamento` | contrato: `nome()` e `pagar(valor)` que devolve `true`/`false` |
| `Pix` | sempre aprova (PoC) |
| `CartaoCredito` | aprova se o valor couber no limite (R$ 100, privado) |
| `ValeRefeicao` | aprova se o valor couber no saldo (R$ 10, privado) |
| `Pedido` | guarda itens, calcula `total()` e `tempo()`, guarda `status` |
| `Main` | pergunta minutos, monta o pedido, cobra e mostra o status |

## Fluxo principal (`Main`)
```
1. pergunta quantos minutos faltam
2. loop: mostra cardápio → aluno escolhe item
         "dá tempo?" = tempo do pedido + item + 3 min para comer <= minutos
3. mostra resumo e total
4. aluno escolhe a forma de pagamento → pagamento.pagar(total)
5. status: PAGO → EM PREPARO → PRONTO
```

## Prova de Aberto/Fechado 
Para adicionar um novo meio de pagamento, como Dinheiro:
1. Criar `Dinheiro.java` implementando `Pagamento`.
2. Registrar em `List.of(...)` no `Main`.

`Pedido`, `Pix` e as demais classes não mudam.
