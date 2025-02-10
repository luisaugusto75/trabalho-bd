package model;

import java.sql.Date;

/**
 * Classe abstrata que representa uma Pessoa.
 */
public abstract class Pessoa {
    private String nome;
    private String CPF;
    private Date nascimento;
    private String imagePath;

    /**
     * Construtor da classe Pessoa.
     *
     * @param nome O nome da pessoa.
     * @param CPF O CPF da pessoa.
     * @param nascimento A data de nascimento da pessoa.
     */
    public Pessoa(String nome, String CPF, Date nascimento) {
        this.nome = nome;
        this.CPF = CPF;
        this.nascimento = nascimento;
    }

    /**
     * Construtor padrão da classe Pessoa.
     */
    public Pessoa() {

    }

    /**
     * Retorna o CPF da pessoa.
     *
     * @return O CPF da pessoa.
     */
    public String getCPF() {
        return CPF;
    }







    /**
     * Define o CPF da pessoa.
     *
     * @param CPF O novo CPF da pessoa.
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**
     * Retorna a data de nascimento da pessoa.
     *
     * @return A data de nascimento da pessoa.
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * Define a data de nascimento da pessoa.
     *
     * @param nascimento A nova data de nascimento da pessoa.
     * @return A nova data de nascimento.
     */
    public Date setNascimento(Date nascimento) {
        this.nascimento = nascimento;
        return nascimento;
    }

    /**
     * Define a idade da pessoa.
     *
     * @param idade A nova idade da pessoa.
     */
    public void setIdade(int idade) {
        this.nascimento = nascimento;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome O novo nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o nome da pessoa.
     *
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o ID da pessoa.
     *
     * @return O ID da pessoa.
     */
    protected int getId() {
        return Integer.parseInt(CPF);
    }
}