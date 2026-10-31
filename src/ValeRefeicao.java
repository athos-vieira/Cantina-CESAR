public class ValeRefeicao implements Pagamento {
    private double saldo = 10.0;
    public String nome() { return "Vale-refeição"; }
    public boolean pagar(double valor) {
        if (valor > saldo) return false;
        saldo -= valor;
        return true;
    }
}
