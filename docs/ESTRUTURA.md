
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

## Dívidas de projeto (o "antes" da comparação)
Problemas conhecidos, a corrigir na PoC final:

| Problema | Princípio violado | Correção planejada |
|---|---|---|
| `Main` faz tela + regra de tempo + fluxo | Responsabilidade Única, Coesão | separar `TelaConsole`, `VerificadorDeTempo`, `CantinaService` |
| `Pedido.status` é `String` pública | Ocultamento de informação | `enum StatusPedido` privado, mudando só por métodos |
| `Pedido.itens` é lista pública | Ocultamento de informação | lista privada, cópia defensiva |
| Pedidos rápidos e preparados na mesma lógica | (requisito da pesquisa) | `FilaDePedidos` com duas filas |
| Preços e tempos espalhados em cada item | Ocultamento de informação | classe `Estimativas` única |
| `tipo()` é `String` | tipagem fraca | `enum TipoItem` |
| `double` para dinheiro | erro de arredondamento | `BigDecimal` |
| `Main` conhece todas as classes concretas e monta as listas à mão | Acoplamento | montar tudo em um só lugar (composition root) e injetar |

## Prova de Aberto/Fechado 
Para adicionar um novo meio de pagamento, como Dinheiro:
1. Criar `Dinheiro.java` implementando `Pagamento`.
2. Registrar em `List.of(...)` no `Main`.

`Pedido`, `Pix` e as demais classes não mudam.
