# Cantina-CESAR

## Onde cada princípio do Cap. 5 aparece

| Princípio | Onde | Como |
|---|---|---|
| **Integridade conceitual** | todos os itens e pagamentos | mesma convenção: interface + classe pequena, mesmos nomes de métodos |
| **Ocultamento de informação** | `Pedido`, `CartaoCredito`, `ValeRefeicao` | `status` e `saldo`/`limite` são `private`; só mudam por métodos |
| **Coesão / Responsabilidade Única** | `VerificadorDeTempo`, `FilaDePedidos`, `TelaConsole` | regra, fila e tela em classes separadas |
| **Acoplamento / Inversão de Dependências** | `Pedido` → `Item`, `Pagamento`; `CantinaService` → `Cardapio` | dependências por interface, injetadas por construtor em `Main` |
| **Segregação de Interfaces** | `Item`, `Pagamento` | interfaces com 4 e 2 métodos; nenhuma classe implementa o que não usa |
| **Composição sobre herança** | `CantinaService`, `Pedido` | "tem um" (fila, verificador, pagamento); nenhuma hierarquia de herança de classes |
| **Demeter** | `Pedido.total()`, `Pedido.tipo()` | falam só com `Item`; ninguém faz `a.getB().getC()` |
| **Aberto/Fechado** | novo item / novo pagamento | basta criar uma classe e registrá-la em `Main`; `Pedido` não muda |
| **Liskov** | `Coxinha` ↔ `Tapioca`; `Pix` ↔ `ValeRefeicao` | qualquer implementação funciona no `Pedido` sem surpresa (contratos documentados) |
```