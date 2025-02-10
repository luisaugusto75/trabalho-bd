package model;
import java.util.Date;
import java.util.Map;

/**
 * Classe que representa uma Venda.
 */
public class Venda {
    public Produto produto;
    public Map<Produto,Integer> produtos;
    public Vendedor vendedor;
    public int quantidade;
    public Cliente cliente;
    public float valor;
    public Date data;
    public int valorTotal;

    /**
     * Construtor da classe Venda.
     *
     * @param produtos Um mapa de produtos e seus preços.
     * @param vendedor O vendedor responsável pela venda.
     * @param quantidade A quantidade de produtos vendidos.
     * @param cliente O cliente que realizou a compra.
     * @param valor O valor total da venda.
     * @param data A data da venda.
     */
    public Venda(Map<Produto, Integer> produtos, Vendedor vendedor, int quantidade, Cliente cliente, float valor, Date data) {
        this.produtos = produtos;
        this.vendedor = vendedor;
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }

    /**
     * Adiciona um produto ao carrinho.
     *
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     */



    /**
     * Retorna o produto da venda.
     *
     * @return O produto da venda.
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Define o produto da venda.
     *
     * @param produto O novo produto da venda.
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Retorna o mapa de produtos da venda.
     *
     * @return O mapa de produtos da venda.
     */

    /**
     * Define o mapa de produtos da venda.
     *
     * @param produtos O novo mapa de produtos da venda.
     */

    /**
     * Retorna o vendedor responsável pela venda.
     *
     * @return O vendedor responsável pela venda.
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * Define o vendedor responsável pela venda.
     *
     * @param vendedor O novo vendedor responsável pela venda.
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Retorna a quantidade de produtos vendidos.
     *
     * @return A quantidade de produtos vendidos.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade de produtos vendidos.
     *
     * @param quantidade A nova quantidade de produtos vendidos.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o cliente que realizou a compra.
     *
     * @return O cliente que realizou a compra.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente que realizou a compra.
     *
     * @param cliente O novo cliente que realizou a compra.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna o valor total da venda.
     *
     * @return O valor total da venda.
     */
    public float getValor() {
        return valor;
    }

    /**
     * Define o valor total da venda.
     *
     * @param valor O novo valor total da venda.
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * Retorna a data da venda.
     *
     * @return A data da venda.
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da venda.
     *
     * @param data A nova data da venda.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna o valor total calculado da venda.
     *
     * @return O valor total calculado da venda.
     */
    public int getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total calculado da venda.
     *
     * @param valorTotal O novo valor total calculado da venda.
     */
    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDigitosCartao(int digitosCartao) {
    }

    public String toString() {
        return "Venda{" +
                "produto=" + produto +
                ", produtos=" + produtos +
                ", vendedor=" + vendedor +
                ", quantidade=" + quantidade +
                ", cliente=" + cliente +
                ", valor=" + valor +
                ", data=" + data +
                ", valorTotal=" + valorTotal +
                '}';
    }
}