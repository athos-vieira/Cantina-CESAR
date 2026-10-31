import java.util.ArrayList;
import java.util.List;

public class Pedido {
    public List<Item> itens = new ArrayList<>();
    public String status = "CRIADO"; // CRIADO -> PAGO -> EM PREPARO -> PRONTO

    public double total() {
        double soma = 0;
        for (Item i : itens) soma += i.preco();
        return soma;
    }

    public int tempo() {
        int soma = 0;
        for (Item i : itens) soma += i.tempo();
        return soma;
    }
}
