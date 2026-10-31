import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Item> cardapio = List.of(new Coxinha(), new Refrigerante(), new Tapioca(), new Cuscuz());
        List<Pagamento> pagamentos = List.of(new Pix(), new CartaoCredito(), new ValeRefeicao());

        System.out.println("=== Cantina CESAR (go-horse) ===");
        System.out.print("Quantos minutos faltam no intervalo? ");
        int minutos = Integer.parseInt(sc.nextLine().trim());

        Pedido pedido = new Pedido();
        while (true) {
            System.out.println("\nCardápio (valores estimados):");
            for (int i = 0; i < cardapio.size(); i++) {
                Item it = cardapio.get(i);
                System.out.println((i + 1) + ") " + it.nome() + " R$ " + it.preco()
                        + " ~" + it.tempo() + " min [" + it.tipo() + "]");
            }
            System.out.print("Escolha um item (0 para finalizar): ");
            int op = Integer.parseInt(sc.nextLine().trim());
            if (op == 0) break;
            if (op < 1 || op > cardapio.size()) {
                System.out.println("Opção inválida.");
                continue;
            }
            Item escolhido = cardapio.get(op - 1);
            // dá tempo? tempo do pedido + item + 3 min para comer
            if (pedido.tempo() + escolhido.tempo() + 3 <= minutos) {
                pedido.itens.add(escolhido);
                System.out.println("Dá tempo! Adicionado: " + escolhido.nome());
            } else {
                System.out.println("Não dá tempo de pegar " + escolhido.nome() + ".");
            }
        }

        if (pedido.itens.isEmpty()) {
            System.out.println("Nenhum item. Até a próxima!");
            return;
        }

        System.out.println("\nResumo do pedido:");
        for (Item it : pedido.itens) System.out.println(" - " + it.nome() + " R$ " + it.preco());
        System.out.println("Total: R$ " + pedido.total());

        System.out.println("\nForma de pagamento:");
        for (int i = 0; i < pagamentos.size(); i++) {
            System.out.println((i + 1) + ") " + pagamentos.get(i).nome());
        }
        System.out.print("Escolha: ");
        int p = Integer.parseInt(sc.nextLine().trim());
        Pagamento pagamento = pagamentos.get(p - 1);

        if (!pagamento.pagar(pedido.total())) {
            System.out.println("Pagamento recusado.");
            return;
        }
        pedido.status = "PAGO";
        System.out.println("Pagamento aprovado. Status: " + pedido.status);

        System.out.println("(Enter para iniciar o preparo)");
        sc.nextLine();
        pedido.status = "EM PREPARO";
        System.out.println("Status: " + pedido.status);

        System.out.println("(Enter para finalizar)");
        sc.nextLine();
        pedido.status = "PRONTO";
        System.out.println("Status: " + pedido.status);
    }
}
