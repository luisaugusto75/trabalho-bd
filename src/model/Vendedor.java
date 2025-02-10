package model;

import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um Vendedor.
 */
public class Vendedor extends Pessoa {

    private String login;
    private String senha;
    ArrayList<Venda> vendas;
    double salario;
    double comissao;

    /**
     * Construtor da classe Vendedor.
     *
     * @param nome O nome do vendedor.
     * @param CPF O CPF do vendedor.
     * @param login O login do vendedor.
     * @param senha A senha do vendedor.
     * @param nascimento A data de nascimento do vendedor.
     * @param salario O salário do vendedor.
     * @param comissao A comissão do vendedor.
     */
    public Vendedor(String nome, String CPF, String login, String senha, Date nascimento, Double salario, double comissao) {
        super(nome, CPF, nascimento);
        this.login = login;
        this.senha = senha;
        this.salario = salario;
        vendas = new ArrayList<Venda>();
        this.comissao = comissao;
    }

    /**
     * Retorna o login do vendedor.
     *
     * @return O login do vendedor.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do vendedor.
     *
     * @param login O novo login do vendedor.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna o salário do vendedor.
     *
     * @return O salário do vendedor.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Define o salário do vendedor.
     *
     * @param salario O novo salário do vendedor.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Retorna a comissão do vendedor.
     *
     * @return A comissão do vendedor.
     */
    public double getComissao() {
        return comissao;
    }

    /**
     * Define a comissão do vendedor.
     *
     * @param comissao A nova comissão do vendedor.
     */
    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    /**
     * Define a senha do vendedor.
     *
     * @param senha A nova senha do vendedor.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Retorna a senha do vendedor.
     *
     * @return A senha do vendedor.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Realiza uma venda.
     *
     * @param produto O produto vendido.
     * @param cliente O cliente que realizou a compra.
     * @param quantidade A quantidade de produtos vendidos.
     * @return A venda realizada.
     */
    public Venda vende(Produto produto, Cliente cliente, int quantidade) {
        Map<Produto, Integer> produtos = new HashMap<>();
        produtos.put(produto, quantidade);

        float valorTotal = produto.getPreco() * quantidade;
        Date now = new Date(new java.util.Date().getTime());
        Venda venda = new Venda(produtos, this, quantidade, cliente, valorTotal, now);

        vendas.add(venda);

        return venda;
    }

    /**
     * Retorna o ID do vendedor.
     *
     * @return O ID do vendedor.
     */
    public int getId(){
        return super.getId();
    }
}