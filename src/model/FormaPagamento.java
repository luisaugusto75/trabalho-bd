package model;

/**
 * Interface que representa uma forma de pagamento.
 */
public interface FormaPagamento {

    /**
     * Realiza o pagamento com o valor especificado.
     *
     * @param valor O valor a ser pago.
     * @return true se o pagamento foi bem-sucedido, false caso contrário.
     */
    boolean pagar(Float valor);

    /**
     * Define o cliente associado a esta forma de pagamento.
     *
     * @param cliente O cliente a ser associado.
     */
    void setCliente(Cliente cliente);

    /**
     * Retorna o cliente associado a esta forma de pagamento.
     *
     * @return O cliente associado.
     */
    String getCliente();
}