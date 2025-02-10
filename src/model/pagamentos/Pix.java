package model.pagamentos;

import model.Cliente;
import model.FormaPagamento;

/**
 * Classe que representa a forma de pagamento por Pix.
 */
public class Pix implements FormaPagamento {

    private Cliente cliente;

    /**
     * Construtor da classe Pix.
     *
     * @param cliente O cliente associado a esta forma de pagamento.
     */
    public Pix(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Realiza o pagamento com o valor especificado.
     *
     * @param valor O valor a ser pago.
     * @return true se o pagamento foi bem-sucedido, false caso contrário.
     */
    @Override
    public boolean pagar(Float valor) {
        return true;
    }

    /**
     * Define o cliente associado a esta forma de pagamento.
     *
     * @param cliente O cliente a ser associado.
     */
    @Override
    public void setCliente(Cliente cliente) {
        // Implementação necessária
    }

    /**
     * Retorna o cliente associado a esta forma de pagamento.
     *
     * @return O cliente associado.
     */
    @Override
    public String getCliente() {
        return "";
    }
}