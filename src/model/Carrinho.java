package model;
import java.util.Map;
import java.util.HashMap;

/**
 * Classe que representa um carrinho de compras.
 */
public class Carrinho {
    private int quantidade = 0;
    private double valorTotal = 0.0;
    private Map<Produto, Integer> conteudo;
    private Cliente cliente;

    /**
     * Construtor da classe Carrinho.
     *
     * @param dono O cliente dono do carrinho.
     */
    public Carrinho(Cliente dono) {
        this.cliente = dono;
        this.conteudo = new HashMap<Produto,Integer>();
    }

    /**
     * Retorna a quantidade total de produtos no carrinho.
     *
     * @return A quantidade total de produtos.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna uma representação em string do carrinho.
     *
     * @return Uma string representando o carrinho.
     */
    public String toString() {
        String carrinho = "";
        for (Produto produto : this.conteudo.keySet()) {
            carrinho += produto.getNome() + " - " + this.conteudo.get(produto) + " unidades\n";
        }
        return carrinho;
    }

    /**
     * Adiciona um produto ao carrinho.
     *
     * @param produto O produto a ser adicionado.
     * @param quantidade A quantidade do produto a ser adicionada.
     * @return 2 se o produto já estava no carrinho e a quantidade foi atualizada, 1 se o produto foi adicionado pela primeira vez.
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        if (this.conteudo.containsKey(produto.getId())) {
            this.conteudo.put(produto, Integer.valueOf(this.conteudo.get(produto) + quantidade));
            this.quantidade += quantidade;
        } else {
            this.conteudo.put(produto, Integer.valueOf(quantidade));
            this.quantidade += quantidade;

        }

    }

    /**
     * Remove um produto do carrinho.
     *
     * @param produto O produto a ser removido.
     * @param quantidade A quantidade do produto a ser removida.
     * @return 1 se a quantidade do produto foi atualizada, 2 se o produto foi removido do carrinho, 0 se a quantidade a ser removida é maior que a quantidade no carrinho ou se o produto não está no carrinho.
     */
    public int removerProduto(Produto produto, int quantidade) {
        if (this.conteudo.containsKey(produto)) {
            if (this.conteudo.get(produto) > quantidade) {
                this.conteudo.put(produto, Integer.valueOf(this.conteudo.get(produto) - quantidade));
                this.quantidade -= quantidade;
                return 1;
            } else if (this.conteudo.get(produto) == quantidade) {
                this.conteudo.remove(produto);
                this.quantidade -= quantidade;
                return 2;
            } else {
                // Tratamento de erro na interface do usuário
                System.out.println("Quantidade de produtos a ser removida maior que a quantidade de produtos no carrinho");
                return 0;
            }
        } else {
            // Tratamento de erro
            System.out.println("O produto selecionado não está no carrinho");
            return 0;
        }
    }

    public double calcularValorTotal() {
        this.valorTotal = 0.0;
        for(Produto produto : this.conteudo.keySet()) {
            this.valorTotal += produto.getPreco() * this.conteudo.get(produto);
        }
        return this.valorTotal;
    }

    /**
     * Retorna o conteúdo do carrinho.
     *
     * @return Um mapa contendo os produtos e suas quantidades no carrinho.
     */
    public Map<Produto, Integer> getConteudo() {
        return conteudo;
    }
}