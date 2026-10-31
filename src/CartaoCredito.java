public class CartaoCredito implements Pagamento {
    private double limite = 100.0;
    public String nome() { return "Cartão de crédito"; }
    public boolean pagar(double valor) {
        if (valor > limite) return false;
        limite -= valor;
        return true;
    }
}
