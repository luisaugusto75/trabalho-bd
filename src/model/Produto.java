package model;

import java.io.File;
import java.io.FileInputStream;

/**
 * Classe que representa um produto.
 */
public class Produto {
    private String nome;
    private float preco;
    private int id;
    private int estoque;
    private String descricao;
    private String categoria;
    private FileInputStream imagem;
    /**
     * Construtor da classe Produto.
     *
     * @param id O ID do produto.
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     * @param estoque A quantidade em estoque do produto.
     * @param descricao A descrição do produto.
     * @param categoria A categoria do produto.
     */
    public Produto(int id, String nome, float preco, int estoque, String descricao, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    /**
     * Construtor da classe Produto.
     *
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     * @param estoque A quantidade em estoque do produto.
     * @param descricao A descrição do produto.
     * @param fis A imagem do produto
     * @param categoria A categoria do produto.
     */
    public Produto(String nome, float preco, int estoque, String descricao, FileInputStream fis, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoria = categoria;
        this.imagem = fis;
    }

    /**
     * Construtor da classe Produto.
     *
     * @param id O id do produto
     * @param nome O nome do produto.
     * @param preco O preço do produto.
     * @param estoque A quantidade em estoque do produto.
     * @param descricao A descrição do produto.
     * @param fis A imagem do produto
     * @param categoria A categoria do produto.
     */
    public Produto(int id,String nome, float preco, int estoque, String descricao, FileInputStream fis, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoria = categoria;
        this.imagem = fis;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco O novo preço do produto.
     * @return O novo preço do produto.
     */
    public float setPreco(float preco) {
        this.preco = preco;
        return preco;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return O preço do produto.
     */
    public float getPreco() {
        return preco;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome O novo nome do produto.
     * @return O novo nome do produto.
     */
    public String setNome(String nome) {
        this.nome = nome;
        return nome;
    }

    /**
     * Retorna o ID do produto.
     *
     * @return O ID do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define a quantidade em estoque do produto.
     *
     * @param estoque A nova quantidade em estoque do produto.
     * @return A nova quantidade em estoque do produto.
     */
    public int setEstoque(int estoque) {
        this.estoque = estoque;
        return estoque;
    }

    /**
     * Retorna a quantidade em estoque do produto.
     *
     * @return A quantidade em estoque do produto.
     */
    public int getEstoque() {
        return estoque;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao A nova descrição do produto.
     * @return A nova descrição do produto.
     */
    public String setDescricao(String descricao) {
        this.descricao = descricao;
        return descricao;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria A nova categoria do produto.
     * @return A nova categoria do produto.
     */
    public String setCategoria(String categoria) {
        this.categoria = categoria;
        return categoria;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return A categoria do produto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Retorna a imagem do produto
     *
     * @return a imagem do produto
     */
    public FileInputStream getImagem() {return imagem;}

}