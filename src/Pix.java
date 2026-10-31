public class Pix implements Pagamento {
    public String nome() { return "Pix"; }
    public boolean pagar(double valor) {
        return true; // PoC: sempre aprova
    }
}
